package br.com.cwi.cwiflix.utils

import android.content.SharedPreferences

/**
 * @author hedo
 */
object SharedPreferencesService {
    lateinit var sharedPreferences: SharedPreferences

    fun write(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun write(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun retrieveString(key: String) = sharedPreferences.getString(key, "")

    fun retrieveInt(key: String) = sharedPreferences.getInt(key, -1)
}