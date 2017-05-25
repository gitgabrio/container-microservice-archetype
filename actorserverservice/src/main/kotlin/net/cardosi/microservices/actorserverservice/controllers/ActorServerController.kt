package net.cardosi.microservices.actorserverservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import java.util.*

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 25/05/17.
 */
/*@Controller*/
class ActorServerController {

/*    @Autowired
    private val msgTemplate: SimpMessageSendingOperations? = null*/


    /**
     * A simple DTO class to encapsulate messages along with their timestamps.
     */
    class MessageDTO(val content: String) {

        val date: Date  = Calendar.getInstance().time

//        init {
//            this.date = Calendar.getInstance().time
//        }
    }


//    /**
//     * Listens the /app/guestbook endpoint and when a message is received, encapsulates it in a MessageDTO instance and relays the resulting object to
//     * the clients listening at the /topic/entries endpoint.
//
//     * @param message the message
//     * *
//     * @return the encapsulated message
//     */
//    @MessageMapping("/guestbook")
//    @SendTo("/topic/entries")
//    fun guestbook(message: String): MessageDTO {
//        println("Received message: " + message)
//        return MessageDTO(message)
//    }

   /* fun casual(message: String) {
        println("casual Received message: " + message)
        msgTemplate!!.convertAndSend("/topic/entries", MessageDTO(message + " from casual"))
    }*/

}