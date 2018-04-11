package com.assist.redmineapp.Login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.assist.redmineapp.Login.Models.UserLoginData
import com.assist.redmineapp.R
import com.assist.redmineapp.data.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var basicLogin: Boolean = true
    private val loginController: LoginController by lazy { LoginController(this@LoginActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    /**
     * authentication using credentials
     */
    fun loginBtnUser(v: View) {
        val currentUser = UserLoginData(text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString(), text_input_domain_Login.text.toString())

        User.instance.userLoginModel = currentUser
        loginController.loginCall(text_input_domain_Login.text.toString(), text_input_e_mail_Login.text.toString(), text_input_password_Login.text.toString())
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
}
