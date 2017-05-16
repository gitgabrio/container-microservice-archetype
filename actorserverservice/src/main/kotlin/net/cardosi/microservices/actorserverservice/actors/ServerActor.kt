@file:JvmName("ServerActor")
package net.cardosi.microservices.actorserverservice.actors

import akka.actor.UntypedActor
import java.lang.Exception

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 07/05/17.
 */

open class ServerActor : UntypedActor() {

    @Throws(Exception::class)
    override fun onReceive(o: Any) {
        if (o is String && !o.equals("OK")) {
            System.out.println("Hello " + o)
            sender.tell("OK", self)
        } else {
            unhandled(o)
        }
    }

}
