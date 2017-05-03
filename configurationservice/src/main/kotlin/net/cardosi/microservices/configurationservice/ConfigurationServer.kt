@file:JvmName("ConfigurationServer")
package net.cardosi.microservices.configurationservice

import net.cardosi.microservices.configurationservice.configurations.ConfigurationConfiguration
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
@Import(ConfigurationConfiguration::class)
open class ConfigurationServer {

    companion object {


        /**
         * Run the application using Spring Boot and an embedded servlet engine.

         * @param args
         * *            Program arguments - ignored.
         */
        @JvmStatic fun main(args: Array<String>) {
            // Tell server to look for web-server.properties or web-server.yml
            //System.setProperty("spring.config.name", "configuration-server")
            SpringApplication.run(ConfigurationServer::class.java, *args)
        }
    }

    // TODO https://spring.io/guides/gs/messaging-stomp-websocket/
    // Implement "push" style web page, to realtime update the page
}
