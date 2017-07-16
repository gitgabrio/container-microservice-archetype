@file:JvmName("TimeConsumingController")

package net.cardosi.microservices.timeconsumingservice.controllers

import net.cardosi.microservices.dto.UserDTO
import net.cardosi.microservices.timeconsumingservice.services.TimeConsumingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
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

    private var counter :Int = 0

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.setAllowedFields("userNumber", "surname", "name")
    }

    /*
     Use for long-polling
     */
    @RequestMapping("/deferredpersons")
    @Async // This means it will run on a different thread
    fun allDeferredUsers(): DeferredResult<List<UserDTO>?> {
        logger.info("web-service allDeferredUsers() invoked")
        val toReturn: DeferredResult<List<UserDTO>?> = DeferredResult()
        Thread({
            val users = timeConsumingService.findAll()
            logger.info("web-service allDeferredUsers() found: " + users!!)
            toReturn.setResult(users)
        }, "MyThread-$counter" ).start()
        counter +=1
        return toReturn
    }
//
//    @RequestMapping("/persons")
//    fun allUsers(): List<UserEntity>? {
//        logger.info("web-service allUsers() invoked")
//        val toReturn = timeConsumingService.findAll()
//        logger.info("web-service allUsers() found: " + toReturn!!)
//        return toReturn
//    }
//
//    @RequestMapping("/listenablepersons")
//    fun allListanableUsers(): ListenableFuture<List<UserEntity>?> {
//        logger.info("web-service allListanableUsers() invoked")
//        val users = timeConsumingService.findAll()
//        logger.info("web-service allListanableUsers() found: " + users!!)
//        val toReturn = AsyncResult(users)
//        return toReturn
//    }


}
