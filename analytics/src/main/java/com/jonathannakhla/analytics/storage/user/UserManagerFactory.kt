package com.jonathannakhla.analytics.storage.user

import android.content.Context

class UserManagerFactory(private val context: Context) {
    companion object {
        private const val USER_MANAGER_STORAGE = "user_manager_storage"
    }

    fun create(): UserManager {
        return UserManagerImpl(context.getSharedPreferences(USER_MANAGER_STORAGE, Context.MODE_PRIVATE))
    }
}