package com.assist.redmineapp.GeneralActivities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.assist.redmineapp.data.User
import com.assist.redmineapp.Models.UserCall
import com.assist.redmineapp.Models.UserLoginData
import com.assist.redmineapp.R
import com.assist.redmineapp.Utils
import com.assist.redmineapp.Utils.getAuthToken
import com.assist.redmineapp.data.RestClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var basicLogin: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        autoLogin()
    }

    private fun autoLogin() {
        val apiKey = Utils.readSharedPreferencesApiKey(this@LoginActivity)
        val domain = Utils.readSharedPreferencesDomain(this@LoginActivity)
        if (apiKey != resources.getString(R.string.defaultApiKey)) {
            User.instance.userLoginModel.domain = domain
            loginCall(apiKey, "")
        }
    }

    /**
     * authentication using credentials
     */
    fun loginBtnUser(v: View) {
        val currentUser = UserLoginData(text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString(), text_input_domain_Login.text.toString())

        User.instance.userLoginModel = currentUser
        loginCall(text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString())

    }

    /**
     * authentication using api key
     */
    fun loginWithApiKey(v: View) {
        if (!basicLogin) {
            text_input_e_mail_Login.hint = "e-mail"
            text_input_password_Login.visibility = View.VISIBLE
            login_btn_withApiKey.text = resources.getString(R.string.login_with_api_key)
            basicLogin = true

        } else {
            text_input_e_mail_Login.hint = "API KEY"
            text_input_password_Login.visibility = View.GONE
            login_btn_withApiKey.text = getString(R.string.basic_login)
            basicLogin = false
        }
    }

    /**]
     * login api call
     */
    private fun loginCall(username: String, password: String) {
        RestClient.instance.api.loginUser(getAuthToken(username, password))
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<UserCall> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(t: UserCall) {
                        Utils.writeSharedPreferencesDomainAndApiKey(this@LoginActivity, text_input_domain_Login.text.toString(), t.user!!.api_key)
                        Log.i("Main", t.user!!.api_key + " " + t.user!!.firstname + " " + t.user!!.login)
                        startActivity(Intent(this@LoginActivity, ProjectsActivities::class.java))
                        finish()
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }


}
