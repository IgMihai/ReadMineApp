package com.assist.redmineapp.GeneralActivities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.assist.redmineapp.Login.LoginController
import com.assist.redmineapp.R


class SplashScreenActivity : AppCompatActivity(), LoginController.ControllerCallback {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    private val loginController: LoginController by lazy { LoginController(this@SplashScreenActivity, this) }

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            loginController.autoLogin()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    override fun userLoggedSuccessfully() {
        finish()
    }

    override fun userLoginError(errorMessage: String) {
        finish()
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}