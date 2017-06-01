package net.cardosi.microservices.timeconsumingservice

import net.cardosi.microservices.test.AbstractControllerTest
import net.cardosi.microservices.timeconsumingservice.configurations.TimeConsumingConfiguration
import org.springframework.test.context.ContextConfiguration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
@ContextConfiguration(classes = arrayOf(TimeConsumingConfiguration::class))
abstract class AbstractTimeConsumingControllerTest : AbstractControllerTest() {

}
