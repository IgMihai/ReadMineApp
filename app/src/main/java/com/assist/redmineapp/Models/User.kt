package com.assist.redmineapp.Models

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
