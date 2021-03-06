package net.cardosi.microservices.configurationservice

import net.cardosi.microservices.configurationservice.configurations.ConfigurationConfiguration
import net.cardosi.microservices.test.AbstractContextTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
@ContextConfiguration(classes = arrayOf(ConfigurationConfiguration::class))
@SpringBootTest
abstract class AbstractConfigurationServiceTest : AbstractContextTest() {

}
