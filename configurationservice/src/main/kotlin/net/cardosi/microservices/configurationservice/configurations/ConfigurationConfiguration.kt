@file:JvmName("ConfigurationConfiguration")

package net.cardosi.microservices.configurationservice.configurations

import net.cardosi.microservices.Services
import net.cardosi.microservices.configurationservice.controllers.HomeController
import net.cardosi.microservices.configurationservice.controllers.UsersController
import net.cardosi.microservices.configurationservice.services.UsersService
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.web.client.RestTemplate

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@ComponentScan("net.cardosi.microservices.configurationservice")
//@PropertySource("configuration-server.properties")
open class ConfigurationConfiguration {

    /**
     * A customized RestTemplate that has the ribbon load balancer build in.
     * @return
     */
    @LoadBalanced
    @Bean
    open fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    /**
     * The UserService encapsulates the interaction with the micro-service.

     * @return A new service instance.
     */
    @Bean
    open fun usersService(): UsersService {
        return UsersService(Services.PERSISTENCE_SERVICE_URL)
    }

    /**
     * Create the controller, passing it the [UsersService] to use.

     * @return
     */
    @Bean
    open fun usersController(): UsersController {
        return UsersController(usersService())
    }

    @Bean
    open fun homeController(): HomeController {
        return HomeController()
    }

//    @Bean
//    open fun callRegistry(): MutableMap<String, String> {
//        return hashMapOf<String, String>()
//    }
}
