@file:JvmName("ConfigurationConfiguration")

package net.cardosi.microservices.timeconsumingservice.configurations

import net.cardosi.microservices.timeconsumingservice.controllers.HomeController
import net.cardosi.microservices.timeconsumingservice.controllers.UsersController
import net.cardosi.microservices.timeconsumingservice.services.UsersService
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.client.RestTemplate

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@ComponentScan("net.cardosi.microservices.timeconsumingservice")
//@PropertySource("configuration-server.properties")
open class ConfigurationConfiguration {

    @Value("\${persistenceservice.url}")
    var persistenceServiceUrl :String = ""

    @Value("\${timeconsumingservice.url}")
    var timeConsumingserviceUrl :String = ""

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
     * A customized RestTemplate that has the ribbon load balancer build in.
     * @return
     */
    @LoadBalanced
    @Bean
    open fun asyncRestTemplate(): AsyncRestTemplate {
        return AsyncRestTemplate()
    }

    /**
     * The UserService encapsulates the interaction with the micro-service.

     * @return A new service instance.
     */
    @Bean
    open fun usersService(): UsersService {
        return UsersService(persistenceServiceUrl, timeConsumingserviceUrl)
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
