@file:JvmName("UsersService")
package net.cardosi.microservices.configurationservice.services

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.lang.Exception
import java.util.*
import java.util.logging.Logger
import javax.annotation.PostConstruct

/**
 * Hide the access to the microservice inside this local service.

 * @author Paul Chapman
 */
@Service
class UsersService(serviceUrl: String) {

    @Autowired
    @LoadBalanced
    protected var restTemplate: RestTemplate? = null

    protected var serviceUrl: String

    protected var logger = Logger.getLogger(UsersService::class.java.name)

    init {
        this.serviceUrl = if (serviceUrl.startsWith("http"))
            serviceUrl
        else
            "http://" + serviceUrl
    }

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show
     * this.
     */
    @PostConstruct
    fun demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        logger.warning("The RestTemplate request factory is " + restTemplate!!.requestFactory.javaClass)
    }

    @Throws(Exception::class)
    fun findAll(): List<UserEntity>? {
        logger.info("findAll() invoked")
        var users: Array<UserEntity>? = null
        try {
            users = restTemplate!!.getForObject(serviceUrl + "/persons/", Array<UserEntity>::class.java)
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
            return null
        }
        if (users == null || users.size == 0)
            return null
        else
            return Arrays.asList(*users)
    }

    @Throws(Exception::class)
    fun findByNumber(userNumber: Integer): UserEntity? {
        logger.info("findByNumber() invoked: for $userNumber")
        try {
            return restTemplate!!.getForObject(serviceUrl + "/persons/{id}", UserEntity::class.java, userNumber)
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
            return null
        }
    }

    fun findByNameAndSurname(surname: String, name: String): List<UserEntity>? {
        logger.info("findByNameAndSurname() invoked:  for {surname} {name}")
        var users: Array<UserEntity>? = null
        try {
            users = restTemplate!!.getForObject(serviceUrl + "/persons/{surname}/{name}", Array<UserEntity>::class.java, surname, name)
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
        }
        if (users == null || users.size == 0)
            return ArrayList()
        else
            return Arrays.asList(*users)
    }

    @Throws(Exception::class)
    fun saveUser(newUser: UserEntity) : UserEntity {
        logger.info("saveUser() invoked: for " + newUser)
        try {
            return restTemplate!!.getForObject(serviceUrl + "/users/add/", UserEntity::class.java, newUser) ?: throw Exception("Failed to save user")
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
            throw e
        }
    }

    //	public User getByNumber(String userNumber) {
    //		User user = restTemplate.getForObject(serviceUrl
    //				+ "/users/{number}", User.class, userNumber);
    //		if (user == null)
    //			throw new UserNotFoundException(userNumber);
    //		else
    //			return user;
    //	}

    //	private User getUser(String userNumber) {
    //
    //	}
}
