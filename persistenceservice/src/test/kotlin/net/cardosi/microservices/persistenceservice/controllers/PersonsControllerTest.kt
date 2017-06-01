package net.cardosi.microservices.persistenceservice.controllers

import net.cardosi.microservices.persistenceservice.AbstractPersistenceControllerTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 01/06/17.
 */
class PersonsControllerTest  : AbstractPersistenceControllerTest(){

    @Autowired
       var personsController: PersonsController? = null
       val CONTENT_TYPE = "application/xml;charset=UTF-8"
       val APPLICATION_JSON = "application/json"
//       private var mockMvc: MockMvc? = null

       /**
        * Before class setup.
        */
       override fun beforeClassSetup() {
           super.beforeClassSetup(personsController!!)
//           assertNotNull(personsController)
//           mockMvc = MockMvcBuilders
//                   .standaloneSetup(personsController)
//                   .build()
       }

    @Test
    fun testById() {

    }

    @Test
    fun testFindBySurnameAndName() {

    }

    @Test
    fun testFindBySurname() {

    }

    @Test
    fun testFindAll() {

    }

    @Test
    fun testSave() {

    }
}