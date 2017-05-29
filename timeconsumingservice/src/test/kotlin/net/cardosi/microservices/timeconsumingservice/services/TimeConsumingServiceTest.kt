package net.cardosi.microservices.timeconsumingservice.services

import net.cardosi.microservices.persistenceservice.entities.UserEntity
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

//    @Override
//    override fun beforeClassSetup() : void {
//
//    }

    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup()
        assertNotNull(timeConsumingService)
    }

    @Test
    fun testFindAll() {
        val retrieved : List<UserEntity>?  = timeConsumingService?.findAll()
        assertNotNull(retrieved)
        assertFalse(retrieved?.isEmpty()!!)
    }
}