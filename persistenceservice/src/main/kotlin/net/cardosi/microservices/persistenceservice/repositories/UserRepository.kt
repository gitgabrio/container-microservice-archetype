@file:JvmName("UserRepository")
package net.cardosi.microservices.persistenceservice.repositories

import net.cardosi.microservices.persistenceservice.entities.UserEntity
import org.springframework.data.repository.CrudRepository

/**
 * Repository for UserEntity data implemented using Spring Data JPA.

 * @author Paul Chapman
 */
interface UserRepository : CrudRepository<UserEntity, Integer> {

    fun findByNameAndSurname(name : String, surname: String) : List<UserEntity>

}
