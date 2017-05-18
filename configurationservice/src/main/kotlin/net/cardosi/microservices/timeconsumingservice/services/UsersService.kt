@file:JvmName("UsersService")

package net.cardosi.microservices.timeconsumingservice.services

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.client.*
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
    private var restTemplate: RestTemplate? = null

    @Autowired
    @LoadBalanced
    private var asyncRestTemplate: AsyncRestTemplate? = null

    private var serviceUrl: String

    private var logger = Logger.getLogger(UsersService::class.java.name)

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
    fun findAsyncAll(): List<UserEntity>? {
        logger.info("findAsyncAll() invoked")
        var users: Array<UserEntity>? = null
        try {
            //TODO Check endpoint path - manage response
            val future: ListenableFuture<String> = asyncRestTemplate!!.execute(serviceUrl + "/persons/", HttpMethod.GET, requestCallback, responseExtractor)
            //waits for the result
            val result = future.get()
            println(result)
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

    fun findBySurnameAndName(surname: String, name: String): UserEntity? {
        logger.info("findBySurnameAndName() invoked:  for $surname $name")
        var user: UserEntity? = null
        try {
            user = restTemplate!!.getForObject(serviceUrl + "/persons/{surname}/{name}", UserEntity::class.java, surname, name)
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
        }
        return user
    }

    fun findBySurname(surname: String): List<UserEntity>? {
        logger.info("findBySurname() invoked:  for $surname ")
        var users: Array<UserEntity>? = null
        try {
            users = restTemplate!!.getForObject(serviceUrl + "/persons/surname/{surname}", Array<UserEntity>::class.java, surname)
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
        }
        if (users == null || users.size == 0)
            return null
        else
            return Arrays.asList(*users)
    }

    @Throws(Exception::class)
    fun saveUser(newUser: UserEntity): UserEntity {
        logger.info("saveUser() invoked: for " + newUser)
        try {
            return restTemplate!!.postForObject(serviceUrl + "/persons/save", newUser, UserEntity::class.java) ?: throw Exception("Failed to save user")
        } catch (e: HttpClientErrorException) { // 404
            // Nothing found
            throw e
        }
    }

    var requestCallback: AsyncRequestCallback = AsyncRequestCallback { arg0 -> System.out.println(arg0.uri) }

    var responseExtractor: ResponseExtractor<String> = ResponseExtractor { arg0 -> arg0.statusText }

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
