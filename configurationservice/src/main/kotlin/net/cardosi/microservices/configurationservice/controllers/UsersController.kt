@file:JvmName("UsersController")

package net.cardosi.microservices.configurationservice.controllers

import net.cardosi.microservices.configurationservice.criterias.SearchCriteria
import net.cardosi.microservices.configurationservice.services.UsersService
import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.StringUtils
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.lang.Exception
import java.util.logging.Logger

/**
 * Client controller, fetches User info from the microservice via
 * [UsersService].
 * @author Paul Chapman
 */
@Controller
class UsersController(

        @Autowired
        protected var usersService: UsersService) {

    protected var logger = Logger.getLogger(UsersController::class.java.name)

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.setAllowedFields("userNumber", "surname", "name")
    }

    @RequestMapping("/users")
    fun allUsers(model: Model): String {
        logger.info("web-service allUsers() invoked")
        val users = usersService.findAll()
        logger.info("web-service allUsers() found: " + users!!)
        if (users != null)
            model.addAttribute("users", users)
        return "users"
    }

    @RequestMapping("/users/{userNumber}")
    fun byNumber(model: Model,
                 @PathVariable("userNumber") userNumber: Integer): String {
        logger.info("web-service byNumber() invoked: " + userNumber)
        try {
            val user = usersService.findByNumber(userNumber)
            logger.info("web-service byNumber() found: " + user)
            model.addAttribute("user", user)
            return "user"
        } catch (e: Exception) {
            logger.severe(e.message)
            model.addAttribute("exception", e)
            return "error"
        }
    }

    @RequestMapping("/users/{surname}/{name}")
    fun byNameAndSurname(model: Model, @PathVariable("surname") surname: String, @PathVariable("name") name: String): String {
        logger.info("web-service findByNameAndSurname() invoked: $surname $name")
        val users = usersService.findByNameAndSurname(surname, name)
        logger.info("web-service byRealm() found: " + users!!)
        model.addAttribute("search", surname)
       // if (users != null)
            model.addAttribute("users", users)
        return "users"
    }

    @RequestMapping(value = "/users/search", method = arrayOf(RequestMethod.GET))
    fun searchForm(model: Model): String {
        model.addAttribute("searchCriteria", SearchCriteria())
        return "userSearch"
    }

    @RequestMapping(value = "/users/dosearch")
    fun doSearch(model: Model, criteria: SearchCriteria,
                 result: BindingResult): String? {
        logger.info("web-service search() invoked: " + criteria)
        criteria.validate(result)
        if (result.hasErrors())
            return "userSearch"
        val userNumber = criteria.userNumber
        if (userNumber != null) {
            return byNumber(model, userNumber)
        } else {
            val surname = criteria.surname
            val name = criteria.name
            return byNameAndSurname(model, surname!!, name!!)
        }
    }

    @RequestMapping(value = "/users/add", method = arrayOf(RequestMethod.GET))
    fun addUser(model: Model) : String {
        val user = UserEntity()
        user.id = Integer(1234)
        user.name = "Pippo"
        user.surname = "Franco"
        model.addAttribute("newUser", user)
        return "userAdd"
    }

    @RequestMapping(value = "/users/doadd")
    fun doAdd(model: Model, toAdd: UserEntity,
              result: BindingResult): String? {
        logger.info("web-service doAdd() invoked $toAdd")
        try {
            val userSaved = usersService.saveUser(toAdd)
            model.addAttribute("user", userSaved)
            return "user"
        } catch (e: Exception) {
            logger.severe(e.message)
            model.addAttribute("exception", e)
            return "error"
        }
    }

}
