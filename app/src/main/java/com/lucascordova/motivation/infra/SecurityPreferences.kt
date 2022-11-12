//SHARED PREFERENCES CODE: In this case, it is saving the username

package com.lucascordova.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    //saves name to a key
    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()

    }

    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
        //if it doesn't find "key", or if it's null (using elvis), returns an empty string
    }
}