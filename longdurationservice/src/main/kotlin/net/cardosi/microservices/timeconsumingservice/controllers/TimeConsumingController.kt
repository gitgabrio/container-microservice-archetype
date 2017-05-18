@file:JvmName("TimeConsumingController")

package net.cardosi.microservices.timeconsumingservice.controllers

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import net.cardosi.microservices.timeconsumingservice.services.TimeConsumingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.logging.Logger

/**
 * TimeConsuming controller, fetches User info from the microservice via
 * [TimeConsumingService].
 * @author Paul Chapman
 */
@RestController
class TimeConsumingController(

        @Autowired
        protected var timeConsumingService: TimeConsumingService) {

    protected var logger = Logger.getLogger(TimeConsumingController::class.java.name)

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.setAllowedFields("userNumber", "surname", "name")
    }

    @RequestMapping("/users")
    fun allUsers(): DeferredResult<List<UserEntity>?> {
        logger.info("web-service allUsers() invoked")
        val users = timeConsumingService.findAll()
        logger.info("web-service allUsers() found: " + users!!)
        val toReturn : DeferredResult<List<UserEntity>?> = DeferredResult()
        toReturn.setResult(users)
        return toReturn
    }


}
