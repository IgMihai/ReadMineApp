package com.assist.redmineapp.data

import com.assist.redmineapp.Login.Models.UserLoginData

/**
 * Created by assist on 30.03.2018.
 */
class User private constructor() {

    companion object {
        var instance: User = User()
    }

    var userLoginModel: UserLoginData = UserLoginData()

    fun getDomainName(): String {
        return userLoginModel.domain
    }

}
