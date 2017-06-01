package net.cardosi.microservices.persistenceservice

import net.cardosi.microservices.persistenceservice.configurations.PersistenceConfiguration
import net.cardosi.microservices.test.AbstractContextTest
import net.cardosi.microservices.test.AbstractControllerTest
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
@ContextConfiguration(classes = arrayOf(PersistenceConfiguration::class)/*, loader = SpringBootContextLoader::class*/)
//@SpringApplicationConfiguration(classes = arrayOf(PersistenceConfiguration::class))
//@SpringBootTest
@DataJpaTest
abstract class AbstractPersistenceControllerTest : AbstractControllerTest() {
}
