@file:JvmName("ActorServerConfiguration")

package net.cardosi.microservices.actorserverservice.configurations

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import net.cardosi.microservices.actorserverservice.actors.ServerActor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@ComponentScan("net.cardosi.microservices.actorserverservice")
open class ActorServerConfiguration {

    @Bean
    open fun actorSystem(): ActorSystem {
        val system = ActorSystem.create("RemoteWorkerSystem", ConfigFactory.defaultApplication())
        system.actorOf(Props.create(ServerActor::class.java), "serverActor")
        return system
    }

}
