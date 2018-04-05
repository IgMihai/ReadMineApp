package com.assist.redmineapp.Models

/**
 * Created by assist on 02.04.2018.
 */

data class UserCall(
        var user: UserGeneralData? = null
)

open class UserGeneralData(var id: Int = 0,
                           var login: String,
                           var firstname: String = "",
                           var lastname: String = "",
                           var created_on: String = "",
                           var last_login_on: String = "",
                           val api_key: String = "")

open class UserLoginData(open var username: String = "", open var password: String = "", open var domain: String = "")

