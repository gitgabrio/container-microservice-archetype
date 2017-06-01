package net.cardosi.microservices.persistenceservice

import net.cardosi.microservices.persistenceservice.configurations.PersistenceConfiguration
import net.cardosi.microservices.test.AbstractContextTest
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
@ContextConfiguration(classes = arrayOf(PersistenceConfiguration::class)/*, loader = SpringBootContextLoader::class*/)
//@SpringApplicationConfiguration(classes = arrayOf(PersistenceConfiguration::class))
//@SpringBootTest
@DataJpaTest
abstract class AbstractPersistenceServiceTest : AbstractContextTest() {
}
