package com.assist.redmineapp.Login

import android.content.Context
import android.content.Intent
import android.util.Log
import com.assist.redmineapp.GeneralActivities.ProjectsActivities
import com.assist.redmineapp.Login.Models.UserCall
import com.assist.redmineapp.Utils
import com.assist.redmineapp.data.RestClient
import com.assist.redmineapp.data.User
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginController(val context: Context) {

    /**
     * Auto-login functionality if user credentials are stocked in SharePreferences
     */
    fun autoLogin() {
        val apiKey = Utils.readSharedPreferencesApiKey(context)
        val domain = Utils.readSharedPreferencesDomain(context)

        if (apiKey != "None") {
            User.instance.userLoginModel.domain = domain
            loginCall(domain, apiKey, "")
        } else {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * APi call user authentication
     *
     * @param domain - domain of the user with which will be logged
     * @param username - username of the user with which will be logged
     * @param password - password of the user with which will be logged
     */
    fun loginCall(domain: String, username: String, password: String) {
        RestClient.instance.api.loginUser(Utils.getAuthToken(username, password))
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<UserCall> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(t: UserCall) {
                        Utils.writeSharedPreferencesDomainAndApiKey(context, domain, t.user!!.api_key)
                        Log.i("Main", t.user!!.api_key + " " + t.user!!.firstname + " " + t.user!!.login)
                        context.startActivity(Intent(context, ProjectsActivities::class.java))
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }
}