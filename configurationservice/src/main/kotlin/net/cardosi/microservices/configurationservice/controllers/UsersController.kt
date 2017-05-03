@file:JvmName("UsersController")

package net.cardosi.microservices.configurationservice.controllers

import net.cardosi.microservices.configurationservice.criterias.SearchCriteria
import net.cardosi.microservices.configurationservice.services.UsersService
import net.cardosi.microservices.persistenceservice.dtos.User
import net.cardosi.microservices.persistenceservice.entities.CompanyEntity
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
        binder.setAllowedFields("userNumber", "searchText")
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
                 @PathVariable("userNumber") userNumber: String): String {
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

    @RequestMapping("/users/realm/{realm}")
    fun byRealm(model: Model, @PathVariable("realm") realm: String): String {
        logger.info("web-service byRealm() invoked: " + realm)
        val users = usersService.findByRealm(realm)
        logger.info("web-service byRealm() found: " + users!!)
        model.addAttribute("search", realm)
        if (users != null)
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
        if (userNumber != null && StringUtils.hasText(userNumber)) {
            return byNumber(model, userNumber)
        } else {
            val searchText = criteria.searchText
            return byRealm(model, searchText!!)
        }
    }

    @RequestMapping(value = "/users/add", method = arrayOf(RequestMethod.GET))
    fun addUser(model: Model) : String {
        val person = UserEntity()
        person.id = "123456"
        val company = CompanyEntity()
        val user = User()
        user.person = person
        user.company = company
        model.addAttribute("newUser", user)
        return "userAdd"
    }

    @RequestMapping(value = "/users/doadd")
    fun doAdd(model: Model, user: UserEntity,
              result: BindingResult): String? {
        logger.info("web-service doAdd() invoked $user")
        try {
            val newUser = User()
            newUser.person = user
            val userSaved = usersService.saveUser(newUser)
            model.addAttribute("user", userSaved)
            return "user"
        } catch (e: Exception) {
            logger.severe(e.message)
            model.addAttribute("exception", e)
            return "error"
        }
    }

}
