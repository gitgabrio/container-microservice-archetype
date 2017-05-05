package net.cardosi.microservices.persistenceservice.controllers


import net.cardosi.microservices.persistenceservice.entities.UserEntity
import net.cardosi.microservices.persistenceservice.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * A RESTFul controller for accessing user information.
 *
 */
@RestController
class PersonsController


/**
 * Create an instance plugging in the respository of Users.

 * @param personRepository An PsAors repository implementation.
 */
@Autowired
constructor(personRepository: PersonRepository) : AbstractEntityController<UserEntity, Integer, PersonRepository>(personRepository) {

    /**
     * Fetch a UserEntity with the specified UserEntity id.

     * @param id A Integer
     * *
     * @return The user if found.
     * *
     * @throws EntityNotFoundException If the number is not recognised.
     */
    @RequestMapping("/persons/{id}")
    override fun byId(@PathVariable("id") id: Integer): UserEntity {
        logger.info("persistence-service byId() invoked: " + id)
        return super.byId(id)
    }

    /**
     * Fetch a <code>UserEntity</code> with the specified surname and name
     * @param surname A String
     *
     *  @param name A String
     *
     * @return The list users found.
     *
     *
     */
    @RequestMapping("/persons/{surname}/{name}")
    fun findBySurnameAndName(@PathVariable("surname") surname: String, @PathVariable("name") name: String): UserEntity {
        logger.info("persistence-service findBySurnameAndName() invoked: {surname} {name}")
        val toReturn = repository.findBySurnameAndName(surname, name)
        logger.info("persistence-service findBySurnameAndName() found: " + toReturn)
        return toReturn
    }

    /**
     * Fetch a <code>List&lt;UserEntity&gt;</code> with the specified surname and name
     * @param surname A String
     *
     *  @param name A String
     *
     * @return The list users found.
     *
     *
     */
    @RequestMapping("/persons/surname/{surname}")
    fun findBySurname(@PathVariable("surname") surname: String): List<UserEntity> {
        logger.info("persistence-service findBySurname() invoked: $surname ")
        val toReturn = repository.findBySurname(surname)
        logger.info("persistence-service findBySurnameAndName() found: " + toReturn)
        return toReturn
    }

    /**
     * Fetch all <code>List&lt;UserEntity&gt;</code> entities
     *
     * @return All the  `UserEntity` found.
     */
    @RequestMapping("/persons")
    override fun findAll(): Iterable<UserEntity> {
        logger.info("persistence-service findAll() invoked")
        return super.findAll()
    }

    /**
     * Save a `UserEntity` entity
     *
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
