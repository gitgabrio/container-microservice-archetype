@file:JvmName("ClientActor")
package net.cardosi.microservices.actorclientservice.actors

import akka.actor.*
import akka.japi.Procedure
import com.netflix.discovery.EurekaClient
import java.lang.Exception
import java.util.*

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 07/05/17.
 */

open class ClientActor(val eurekaClient: EurekaClient) : UntypedActor() {

    init {
        sendIdentifyRequest()
    }

    private var remoteActor: ActorRef? = null
    internal var path: String? = null

    private fun sendInput() {
        println("Send a message or quit")
        var scanner : Scanner = Scanner(System.`in`)
        while(true) {
            var input : String = scanner.nextLine()
            if (input.equals("quit")) {
                println("Bye")
                break
            }
            remoteActor?.tell(input, self)
        }
    }

    private fun sendIdentifyRequest() {
        val nextServerInfo =eurekaClient.getNextServerFromEureka("akka.server.actor", false)
        val servicePort = nextServerInfo.metadata["port"]
       // val serviceUrl = nextServerInfo.hostName + ":$servicePort"
        val serviceUrl = "127.0.0.1:$servicePort"

        path = "akka.tcp://RemoteWorkerSystem@$serviceUrl/user/serverActor"
        //        path = "akka.tcp://RemoteWorkerSystem@localhost" + ":" + nextServerInfo.getPort() + "/user/remoteActor";
        println("Sending message to " + path)

        context.actorSelection(path).tell(Identify("ciccio"), self)
//        context.actorSelection(path).tell("CICCIO", self)
    }

    @Throws(Exception::class)
    override fun onReceive(message: Any) {
        if (message is ActorIdentity) {
            remoteActor = message.ref
            if (remoteActor == null) {
                println("Remote actor not available: " + path!!)
                sendIdentifyRequest()
            } else {
                context.watch(remoteActor)
                context.become(active, true)
//                (remoteActor as ActorRef).tell("Ciao, secco", self)
                sendInput()
            }
        } else if (message is ReceiveTimeout) {
            sendIdentifyRequest()
        } else if (message is String) {
            println("Received ${message}")
        } else {
            println("Not ready yet")

        }
    }

    internal var active: Procedure<Any> = Procedure { message ->
        if (message is String) {
            try {
                remoteActor!!.tell(message, self)
            } catch (e: Exception) {
                e.printStackTrace()
            }

//        } else if (message is MessageProto.ResultMessage) {
//            System.out.println((message as MessageProto.ResultMessage).getStatus())
        } else if (message is Terminated) {
            println("OMG, they killed Kenny, searching on eureka ")
            context.unwatch(remoteActor)
            context.unbecome()
            sendIdentifyRequest()
        } else {
            println(message.javaClass.name)
            unhandled(message)
        }
    }
}
