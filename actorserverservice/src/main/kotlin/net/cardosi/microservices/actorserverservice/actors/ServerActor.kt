@file:JvmName("ServerActor")
package net.cardosi.microservices.actorserverservice.actors

import akka.actor.*
import akka.japi.Procedure
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryManager
import java.lang.Exception

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 07/05/17.
 */

open class ServerActor : UntypedActor() {


    @Throws(Exception::class)
    override fun onReceive(message: Any) {
        if (message is String) {
            System.out.println("Hello " + message)
            sender.tell("Gotcha " + message, self)
        } else {
            unhandled(message)
        }
    }

}
