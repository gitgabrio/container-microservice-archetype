package net.cardosi.microservices.dto

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 27/05/17.
 */
data class UserDTO(val id: Int?, val name : String, val surname : String) : InterfaceDTO {

    override fun toString(): String{
        return "UserDTO(id=$id, name='$name', surname='$surname')"
    }
}