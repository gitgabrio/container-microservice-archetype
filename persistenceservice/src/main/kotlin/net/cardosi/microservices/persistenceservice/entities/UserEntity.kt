@file:JvmName("UserEntity")
package net.cardosi.microservices.persistenceservice.entities


import javax.persistence.*

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 31/10/2016.
 */
@Entity
@Table(name = "persons"/*, schema = "testdb"*/)
class UserEntity : InterfaceEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    var id: Integer? = null

    @Basic
    @Column(name = "name")
    var name: String? = null

    @Basic
    @Column(name = "surname")
    var surname: String? = null


    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is UserEntity) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false

        return true
    }

    override fun hashCode(): Int{
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        return result
    }


}
