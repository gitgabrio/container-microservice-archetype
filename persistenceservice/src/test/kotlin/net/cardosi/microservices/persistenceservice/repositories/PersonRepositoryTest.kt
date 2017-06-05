package net.cardosi.microservices.persistenceservice.controllers

import net.cardosi.microservices.persistenceservice.AbstractPersistenceServiceTest
import net.cardosi.microservices.persistenceservice.entities.UserEntity
import net.cardosi.microservices.persistenceservice.repositories.PersonRepository
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 01/06/17.
 */
open class PersonRepositoryTest : AbstractPersistenceServiceTest() {

    @Autowired
    var personRepository: PersonRepository? = null

    /**
     * Before class setup.
     */
    override fun beforeClassSetup() {
        super.beforeClassSetup()
        assertNotNull(personRepository)
    }

    @Test
    fun testById() {
        var retrieved = personRepository?.findOne(1)
        assertNotNull(retrieved)
        retrieved = personRepository?.findOne(100)
        assertNull(retrieved)
    }

    @Test
    fun testFindBySurnameAndName() {
        var retrieved = personRepository?.findBySurnameAndName("Lee", "Keri")
        assertNotNull(retrieved)
        retrieved = personRepository?.findBySurnameAndName("Loa", "Keri")
        assertNull(retrieved)
    }

    @Test
    fun testFindBySurname() {
        var retrieved: List<UserEntity>? = personRepository?.findBySurname("Lee")
        assertNotNull(retrieved)
        assertTrue(retrieved!!.size == 1)
        retrieved = personRepository?.findBySurname("Lea")
        assertNotNull(retrieved)
        assertTrue(retrieved!!.isEmpty())
    }


    @Test
    fun testFindAll() {
        var retrieved: Iterable<UserEntity>? = personRepository?.findAll()
        assertNotNull(retrieved)
    }

    @Test
    fun testSave() {
        var toSave = UserEntity()
        toSave.name = "NAME"
        toSave.surname = "SURNAME"
        var retrieved = personRepository?.save(toSave)
        assertNotNull(retrieved)
    }
}