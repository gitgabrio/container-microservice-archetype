@file:JvmName("HomeController")
package net.cardosi.microservices.actorserverservice.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Home page controller.

 * @author Paul Chapman
 */
@Controller
class HomeController {

    @RequestMapping("/")
    fun home(): String {
        return "index"
    }

}
