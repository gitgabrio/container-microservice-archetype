package net.cardosi.microservices.persistenceservice.controllers

//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import net.cardosi.microservices.persistenceservice.AbstractPersistenceControllerTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 01/06/17.
 */
//@SpringBootTest
open class HomeControllerTest : AbstractPersistenceControllerTest() {

    @Autowired
    var homeController: HomeController? = null
    val CONTENT_TYPE = "application/xml;charset=UTF-8"
    val APPLICATION_JSON = "application/json"
   // private var mockMvc: MockMvc? = null

    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup(homeController!!)
//        assertNotNull(homeController)
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(homeController)
//                .build()
    }

    @Test
    fun testHomeDirectCall() {
        val retrieved = homeController?.home()
        assertNotNull(retrieved)
    }

    @Test
    fun testHomeRESTCall() {
        val mvcResult = mockMvc?.perform(get("/"))?.andExpect(status().isOk)?.andExpect(forwardedUrl("index"))
        assertNotNull(mvcResult)
    }


}