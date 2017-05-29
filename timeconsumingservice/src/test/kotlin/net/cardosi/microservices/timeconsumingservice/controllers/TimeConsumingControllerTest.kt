package net.cardosi.microservices.timeconsumingservice.controllers

import net.cardosi.microservices.timeconsumingservice.AbstractTimeConsumingServiceTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.request
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
class TimeConsumingControllerTest : AbstractTimeConsumingServiceTest() {

    @Autowired
    var timeConsumingController: TimeConsumingController? = null
    val CONTENT_TYPE = "Content-Type"
    val APPLICATION_JSON = "application/json"
    private var mockMvc: MockMvc? = null

    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup()
        assertNotNull(timeConsumingController)
        mockMvc = MockMvcBuilders
                .standaloneSetup(timeConsumingController)
                .build();
    }

    @Test
    fun testAllDeferredUsersDirectCall() {
        val retrieved = timeConsumingController?.allDeferredUsers()
        assertNotNull(retrieved)

    }

    @Test
    fun testAllDeferredUsersRESTCall() {
        val mvcResult = mockMvc?.perform(get("/deferredpersons"))?.andExpect(request().asyncStarted())?.andReturn()
        mvcResult?.asyncResult
        mockMvc?.perform(asyncDispatch(mvcResult))?.andExpect(status().isOk)?.andExpect(content().contentType("application/xml;charset=UTF-8"))//?.andExpect(content().string(expectedResult))

    }
}