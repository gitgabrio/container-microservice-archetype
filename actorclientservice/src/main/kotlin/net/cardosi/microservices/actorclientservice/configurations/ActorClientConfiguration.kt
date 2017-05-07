@file:JvmName("ActorClientConfiguration")

package net.cardosi.microservices.actorclientservice.configurations

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import net.cardosi.microservices.actorclientservice.actors.ClientActor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@ComponentScan("net.cardosi.microservices.actorclientservice")
open class ActorClientConfiguration {


    @Bean
    open fun actorSystem(): ActorSystem {
        val system = ActorSystem.create("Client", ConfigFactory.load("application"))
        val actor = system.actorOf(Props.create(ClientActor::class.java), "clientActor")
        return system
    }


}
