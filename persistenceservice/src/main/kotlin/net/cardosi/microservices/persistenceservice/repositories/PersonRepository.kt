@file:JvmName("PersonRepository")
package net.cardosi.microservices.persistenceservice.repositories

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.data.repository.CrudRepository

/**
 * Repository for UserEntity data implemented using Spring Data JPA.

 * @author Paul Chapman
 */
interface PersonRepository : CrudRepository<UserEntity, Integer> {

    fun findBySurnameAndName(surname : String, name: String) : UserEntity

    fun findBySurname(surname : String) : List<UserEntity>
}
