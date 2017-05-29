package net.cardosi.microservices.timeconsumingservice.configurations

import net.cardosi.microservices.timeconsumingservice.AbstractTimeConsumingServiceTest
import net.cardosi.microservices.timeconsumingservice.controllers.HomeController
import net.cardosi.microservices.timeconsumingservice.controllers.TimeConsumingController
import net.cardosi.microservices.timeconsumingservice.services.TimeConsumingService
import org.junit.Test

import org.junit.Assert.*
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 27/05/17.
 */
class TimeConsumingConfigurationTest : AbstractTimeConsumingServiceTest() {

    @Autowired
    var timeConsumingService : TimeConsumingService? = null

    @Autowired
    var timeConsumingController : TimeConsumingController? = null

    @Autowired
    var homeController : HomeController? = null

    @Test
    fun testTimeConsumingService() {
        assertNotNull(timeConsumingService)
    }

    @Test
    fun testTimeConsumingController() {
        assertNotNull(timeConsumingController)
    }

    @Test
    fun testHomeController() {
        assertNotNull(homeController)
    }
}