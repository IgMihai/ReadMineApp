package com.assist.redmineapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.assist.redmineapp.Models.UserGeneralData
import com.assist.redmineapp.Models.UserLoginData
import com.assist.redmineapp.Models.User
import com.assist.redmineapp.data.RestClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity() {
    var basicLogin: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * authentication using credentials
     */
    fun loginBtnUser(v: View) {
        var currentUser = UserLoginData(text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString(), text_input_domain_Login.text.toString())

        User.instance.userLoginModel = currentUser
        loginCall()
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
    private fun loginCall() {
        RestClient.instance.api.loginUser(getAuthToken(text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString()))
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<UserGeneralData> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(t: UserGeneralData) {
                        Log.i("Main", t.api_key + " " + t.firstname + " " + t.login)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }

    /**
     * gets the token for basic authentication
     */
    private fun getAuthToken(username: String, password: String): String {
        var data = ByteArray(0)
        try {
            data = ("$username:$password").toByteArray(Charsets.UTF_8)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return "Basic " + android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP)
    }
}
