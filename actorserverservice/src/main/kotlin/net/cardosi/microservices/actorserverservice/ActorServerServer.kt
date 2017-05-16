@file:JvmName("ActorServerServer")
package net.cardosi.microservices.actorserverservice

import net.cardosi.microservices.actorserverservice.configurations.ActorServerConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

/**
 * Users web-server. Works as a microservice client, fetching data from the
 * User-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
@Import(ActorServerConfiguration::class)
open class ActorServerServer {

    companion object {


        /**
         * Run the application using Spring Boot and an embedded servlet engine.

         * @param args
         * *            Program arguments - ignored.
         */
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(ActorServerServer::class.java, *args)
        }
    }

    // TODO https://spring.io/guides/gs/messaging-stomp-websocket/
    // Implement "push" style web page, to realtime update the page
}
