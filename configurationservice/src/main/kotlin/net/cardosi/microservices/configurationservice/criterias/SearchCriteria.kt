@file:JvmName("SearchCriteria")
package net.cardosi.microservices.configurationservice.criterias

import org.springframework.util.StringUtils
import org.springframework.validation.Errors
import java.lang.NumberFormatException

class SearchCriteria {
    var userNumber: String? = null

    var searchText: String? = null

    val isValid: Boolean
        get() {
            if (StringUtils.hasText(userNumber))
                return !StringUtils.hasText(searchText)
            else
                return StringUtils.hasText(searchText)
        }

    fun validate(errors: Errors): Boolean {
        if (StringUtils.hasText(userNumber)) {
            if (userNumber!!.length < 1)
                errors.rejectValue("userNumber", "badFormat",
                        "User number should be at least 1 digit")
            else {
                try {
                    Integer.parseInt(userNumber!!)
                } catch (e: NumberFormatException) {
                    errors.rejectValue("userNumber", "badFormat",
                            "User number should be only digits")
                }

            }

            if (StringUtils.hasText(searchText)) {
                errors.rejectValue("searchText", "nonEmpty",
                        "Cannot specify user number and search text")
            }
        } else if (StringUtils.hasText(searchText)) {
        }// Nothing to do
        else {
            errors.rejectValue("userNumber", "nonEmpty",
                    "Must specify either an user number or search text")

        }

        return errors.hasErrors()
    }

    override fun toString(): String {
        return (if (StringUtils.hasText(userNumber))
            "number: " + userNumber!!
        else
            "") + if (StringUtils.hasText(searchText))
            " text: " + searchText!!
        else
            ""
    }
}
