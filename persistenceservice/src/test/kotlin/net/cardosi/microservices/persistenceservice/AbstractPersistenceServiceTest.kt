package net.cardosi.microservices.persistenceservice

import net.cardosi.microservices.persistenceservice.configurations.PersistenceConfiguration
import net.cardosi.microservices.test.AbstractContextTest
import org.springframework.test.context.ContextConfiguration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
@ContextConfiguration(classes = arrayOf(PersistenceConfiguration::class))
abstract class AbstractPersistenceServiceTest : AbstractContextTest() {

}
