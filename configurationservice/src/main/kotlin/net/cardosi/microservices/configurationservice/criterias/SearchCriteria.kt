@file:JvmName("SearchCriteria")
package net.cardosi.microservices.configurationservice.criterias

import org.springframework.validation.Errors

class SearchCriteria {
    var userNumber: Integer? = null

    var surname: String? = null

    var name: String? = null

    val isValid: Boolean
        get() {
            // TODO IMPLEMENTATION
            return true
//            if (StringUtils.hasText(userNumber))
//                return !StringUtils.hasText(surname)
//            else
//                return StringUtils.hasText(surname)
        }

    fun validate(errors: Errors): Boolean {
        // TODO IMPLEMENTATION
//        if (userNumber) {
//            if (userNumber!!.length < 1)
//                errors.rejectValue("userNumber", "badFormat",
//                        "User number should be at least 1 digit")
//            else {
//                try {
//                    Integer.parseInt(userNumber!!)
//                } catch (e: NumberFormatException) {
//                    errors.rejectValue("userNumber", "badFormat",
//                            "User number should be only digits")
//                }
//
//            }
//
//            if (StringUtils.hasText(surname)) {
//                errors.rejectValue("surname", "nonEmpty",
//                        "Cannot specify user number and search text")
//            }
//        } else if (StringUtils.hasText(surname)) {
//        }// Nothing to do
//        else {
//            errors.rejectValue("userNumber", "nonEmpty",
//                    "Must specify either an user number or search text")
//
//        }

        return errors.hasErrors()
    }

    override fun toString(): String {
        return "SearchCriteria(userNumber=$userNumber, surname=$surname, name=$name)"
    }


}
