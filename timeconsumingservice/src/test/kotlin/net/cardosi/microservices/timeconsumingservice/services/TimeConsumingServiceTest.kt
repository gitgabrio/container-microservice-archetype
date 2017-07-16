package net.cardosi.microservices.timeconsumingservice.services

import net.cardosi.microservices.dto.UserDTO
import net.cardosi.microservices.timeconsumingservice.AbstractTimeConsumingServiceTest
import org.junit.Test

import org.junit.Assert.*
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 27/05/17.
 */
class TimeConsumingServiceTest : AbstractTimeConsumingServiceTest() {

    @Autowired
    var timeConsumingService: TimeConsumingService? = null


    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup()
        assertNotNull(timeConsumingService)
    }

    @Test
    fun testFindAll() {
        val retrieved : List<UserDTO>?  = timeConsumingService?.findAll()
        assertNotNull(retrieved)
        assertFalse(retrieved?.isEmpty()!!)
    }
}