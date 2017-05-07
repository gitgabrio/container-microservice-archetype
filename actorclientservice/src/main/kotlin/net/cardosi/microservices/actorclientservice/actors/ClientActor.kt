@file:JvmName("ClientActor")
package net.cardosi.microservices.actorclientservice.actors

import akka.actor.*
import akka.japi.Procedure
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryManager
import java.lang.Exception

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 07/05/17.
 */

open class ClientActor : UntypedActor() {


    private var remoteActor: ActorRef? = null
    internal var path: String? = null

    init {
        sendIdentifyRequest()
    }

    private fun sendIdentifyRequest() {
        DiscoveryManager.getInstance().initComponent(
                MyDataCenterInstanceConfig(),
                DefaultEurekaClientConfig())
        val nextServerInfo = DiscoveryManager.getInstance().eurekaClient.getNextServerFromEureka("akka.server.actor", false)
        val serviceUrl = nextServerInfo.instanceId
        path = "akka.tcp://RemoteWorkerSystem@" + serviceUrl + ":" + nextServerInfo.port + "/user/remoteActor"
        println("Sending message to server " + serviceUrl)
        getContext().actorSelection(path).tell(Identify(path), getSelf())
    }

    @Throws(Exception::class)
    override fun onReceive(message: Any) {
        if (message is ActorIdentity) {
            remoteActor = message.ref
            if (remoteActor == null) {
                println("Remote actor not available: " + path!!)
                sendIdentifyRequest()
            } else {
                getContext().watch(remoteActor)
                getContext().become(active, true)
            }

        } else if (message is ReceiveTimeout) {
            sendIdentifyRequest()
        } else {
            println("Not ready yet")
        }
    }

    internal var active: Procedure<Any> = Procedure {
        //            if (message instanceof MessageProto.Message) {
        //                try {
        //                    remoteActor.tell(message, getSelf());
        //                } catch (Exception e) {
        //                    e.printStackTrace();
        //                }
        //            } else if (message instanceof MessageProto.ResultMessage) {
        //                System.out.println(((MessageProto.ResultMessage) message).getStatus());
        //            } else if (message instanceof Terminated) {
        //                System.out.println("OMG, they killed Kenny, searching on eureka ");
        //                getContext().unwatch(remoteActor);
        //                getContext().unbecome();
        //                sendIdentifyRequest();
        //            } else {
        //                System.out.println(message.getClass().getName());
        //                unhandled(message);
        //            }
    }
}
