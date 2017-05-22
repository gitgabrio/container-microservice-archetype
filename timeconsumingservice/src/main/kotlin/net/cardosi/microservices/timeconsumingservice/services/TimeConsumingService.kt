@file:JvmName("TimeConsumingService")

package net.cardosi.microservices.timeconsumingservice.services

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.stereotype.Service
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*
import java.util.logging.Logger

/**
 * Hide the access to the microservice inside this local service.

 * @author Paul Chapman
 */
@Service
class TimeConsumingService {


    protected var logger = Logger.getLogger(TimeConsumingService::class.java.name)


    @Throws(Exception::class)
    fun findAll(): List<UserEntity>? {
        logger.info("findAll() invoked")
        val toReturn: ArrayList<UserEntity> = ArrayList()
        repeat(50) { i ->
            val toAdd: UserEntity = createUserEntity(i)
            toReturn.add(toAdd)
            try {
                Thread.sleep(1000)
            } catch (e : InterruptedException) {
                println("I've been interrupted")
            }
        }
        return toReturn

    }

    private fun createUserEntity(id: Int): UserEntity {
        val toReturn = UserEntity()
        toReturn.id = Integer(id)
        toReturn.name = getSaltString (Random().nextInt(7) + 3)
        toReturn.surname = getSaltString(Random().nextInt(7) + 3)
        return toReturn

    }

    private fun getSaltString(size: Int): String {
        val SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val salt = StringBuilder()
        val rnd = Random()
        while (salt.length < size) { // length of the random string.
            val index = rnd.nextInt(SALTCHARS.length) as Int
            salt.append(SALTCHARS[index])
        }
        val saltStr = salt.toString()
        return saltStr

    }
}
