package com.jonathannakhla.analytics.storage.user

import android.content.SharedPreferences
import java.util.*

class UserManagerImpl(private val sharedPreferences: SharedPreferences) :
    UserManager {
    companion object {
        private const val USER_ID_KEY = "user_id"
    }
    override fun getUserId(): String {
        return if (sharedPreferences.contains(USER_ID_KEY)) {
            sharedPreferences.getString(USER_ID_KEY, "")!!
        } else {
            val newUserId = UUID.randomUUID().toString()
            sharedPreferences.edit().putString(USER_ID_KEY, newUserId).apply()
            newUserId
        }
    }
}