package com.jonathannakhla.nytimesdemo.storage

import android.content.Context

class TopStoriesStorageFactory(private val context: Context) {

    companion object {
        private const val STORAGE_NAME = "TopStoriesStorage"
    }

    fun create(): TopStoriesStorage {
        return TopStoriesStorageImpl(context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE))
    }
}