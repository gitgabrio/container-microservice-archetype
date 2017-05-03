package net.cardosi.microservices.persistenceservice.controllers



import net.cardosi.microservices.persistenceservice.entities.UserEntity
import net.cardosi.microservices.persistenceservice.exceptions.EntityNotFoundException
import net.cardosi.microservices.persistenceservice.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * A RESTFul controller for accessing user information.
 *
 */
@RestController
class UserController


/**
 * Create an instance plugging in the respository of Users.

 * @param userRepository An PsAors repository implementation.
 */
@Autowired
constructor(userRepository: UserRepository) : AbstractEntityController<UserEntity, String, UserRepository>(userRepository) {

    /**
     * Fetch a UserEntity with the specified UserEntity id.

     * @param id A String, PsAor id.
     * *
     * @return The user if found.
     * *
     * @throws EntityNotFoundException If the number is not recognised.
     */
    @RequestMapping("/persons/{id}")
    override fun byId(@PathVariable("id") id: String): UserEntity {
        logger.info("persistence-service byId() invoked: " + id)
        return super.byId(id)
    }

    /**
     * Fetch all `List&lt;UserEntity&gt;` entities

     * @return All the  `UserEntity` found.
     */
    @RequestMapping("/persons")
    override fun findAll(): Iterable<UserEntity> {
        logger.info("persistence-service findAll() invoked")
        return super.findAll()
    }

    /**
     * Save a `UserEntity` entity

     * @param toSave `UserEntity` entity  to save
     * *
     * @return The saved `UserEntity;` entity
     */
    @RequestMapping(value = "/persons/save", method = arrayOf(RequestMethod.POST))
    override fun save(@RequestBody toSave: UserEntity): UserEntity {
        logger.info("persistence-service save() invoked: " + toSave)
        return super.save(toSave)
    }
}
