package net.cardosi.microservices.timeconsumingservice.controllers

import net.cardosi.microservices.timeconsumingservice.AbstractTimeConsumingControllerTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 29/05/17.
 */
class TimeConsumingControllerTest : AbstractTimeConsumingControllerTest() {

    @Autowired
    var timeConsumingController: TimeConsumingController? = null
    val CONTENT_TYPE = "application/xml;charset=UTF-8"

    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup(timeConsumingController!!)
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
        mockMvc?.perform(asyncDispatch(mvcResult))?.andExpect(status().isOk)?.andExpect(content().contentType(CONTENT_TYPE))//?.andExpect(content().string(expectedResult))

    }
}