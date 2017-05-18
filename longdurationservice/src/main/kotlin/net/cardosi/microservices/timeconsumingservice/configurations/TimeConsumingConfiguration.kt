@file:JvmName("TimeConsumingConfiguration")

package net.cardosi.microservices.timeconsumingservice.configurations

import net.cardosi.microservices.timeconsumingservice.controllers.HomeController
import net.cardosi.microservices.timeconsumingservice.controllers.TimeConsumingController
import net.cardosi.microservices.timeconsumingservice.services.TimeConsumingService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@ComponentScan("net.cardosi.microservices.timeconsumingservice")
open class TimeConsumingConfiguration {



    /**
     * The TimeConsumingService encapsulates the interaction with the micro-service.

     * @return A new service instance.
     */
    @Bean
    open fun timeConsumingService(): TimeConsumingService {
        return TimeConsumingService()
    }

    /**
     * Create the controller, passing it the [TimeConsumingService] to use.

     * @return
     */
    @Bean
    open fun timeConsumingController(): TimeConsumingController {
        return TimeConsumingController(timeConsumingService())
    }

    @Bean
    open fun homeController(): HomeController {
        return HomeController()
    }

}
