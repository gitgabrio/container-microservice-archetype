@file:JvmName("PersistenceServer")
package net.cardosi.microservices.persistenceservice


import net.cardosi.microservices.persistenceservice.configurations.PersistenceConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Import

import java.util.logging.Logger

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 *
 *
 * Note that the configuration for this application is imported from
 * [PersistenceConfiguration]. This is a deliberate separation of concerns.

 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(PersistenceConfiguration::class)
open class PersistenceServer {

    protected var logger = Logger.getLogger(PersistenceServer::class.java.name)

    companion object {

        /**
         * Run the application using Spring Boot and an embedded servlet engine.

         * @param args
         * *            Program arguments - ignored.
         */
        @JvmStatic fun main(args: Array<String>) {
            // Tell server to look for users-server.properties or
            // persistence-server.bkp
            //System.setProperty("spring.config.name", "persistence-server")
            SpringApplication.run(PersistenceServer::class.java, *args)
        }
    }
}
