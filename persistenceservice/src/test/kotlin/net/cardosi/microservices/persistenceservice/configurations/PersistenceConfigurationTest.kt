package net.cardosi.microservices.persistenceservice.configurations

import net.cardosi.microservices.persistenceservice.AbstractPersistenceServiceTest
import net.cardosi.microservices.persistenceservice.controllers.HomeController
import net.cardosi.microservices.persistenceservice.controllers.PersonsController
import net.cardosi.microservices.persistenceservice.repositories.PersonRepository
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
class PersistenceConfigurationTest : AbstractPersistenceServiceTest() {

    @Autowired
    var dataSource: DataSource? = null

    @Autowired
    var homeController: HomeController? = null

    @Autowired
    var personsController: PersonsController? = null

//    @Autowired
//    var personRepository: PersonRepository? = null


    @Test
    fun testDataSource() {
        assertNotNull(dataSource)
    }

    @Test
    fun testHomeController() {
        assertNotNull(homeController)
    }

    @Test
    fun testPersonsController() {
        assertNotNull(personsController)
    }

//    @Test
//    fun testPersonRepository() {
//        assertNotNull(personRepository)
//    }

}