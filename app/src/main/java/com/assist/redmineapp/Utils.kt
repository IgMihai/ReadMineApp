package com.assist.redmineapp

import android.content.Context
import android.preference.PreferenceManager
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.UnsupportedEncodingException

object Utils {

    fun deleteSharePreferences(context: Context) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun readSharedPreferencesDomain(context: Context): String {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultValue: String = context.resources.getString(R.string.defaultDomain)
        return sharedPref.getString(context.getString(R.string.changeDomain), defaultValue)
    }

    fun readSharedPreferencesApiKey(context: Context): String {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultValue: String = context.resources.getString(R.string.defaultApiKey)
        return sharedPref.getString(context.getString(R.string.changeApiKey), defaultValue)
    }


    fun writeSharedPreferencesDomainAndApiKey(context: Context, domain: String, apiKey: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.putString(context.resources.getString(R.string.changeDomain), domain)
        editor.putString(context.resources.getString(R.string.changeApiKey), apiKey)
        editor.apply()
    }
    /**
     * gets the token for basic authentication
     */
    fun getAuthToken(username: String, password: String): String {
        var data = ByteArray(0)
        try {
            data = ("$username:$password").toByteArray(Charsets.UTF_8)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return "Basic " + android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP)
    }

}