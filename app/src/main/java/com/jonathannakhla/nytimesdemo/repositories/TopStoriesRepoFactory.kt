package com.jonathannakhla.nytimesdemo.repositories

import android.content.Context
import com.jonathannakhla.nytimesdemo.network.TopStoriesApiFactory
import com.jonathannakhla.nytimesdemo.storage.TopStoriesStorageFactory

class TopStoriesRepoFactory(private val context: Context) {

    fun create(): TopStoriesRepo {
        val api = TopStoriesApiFactory(context.applicationContext).create()
        val localStorage = TopStoriesStorageFactory(context.applicationContext).create()

        return TopStoriesRepoImpl(api, localStorage)
    }
}