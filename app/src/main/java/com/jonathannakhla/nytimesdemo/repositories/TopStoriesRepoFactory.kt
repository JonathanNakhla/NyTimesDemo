package com.jonathannakhla.nytimesdemo.repositories

import android.content.Context
import com.jonathannakhla.nytimesdemo.api.TopStoriesApiFactory
import com.jonathannakhla.nytimesdemo.storage.TopStoriesStorageFactory

class TopStoriesRepoFactory(private val context: Context) {

    fun create(): TopStoriesRepo {
        val api = TopStoriesApiFactory().create()
        val localStorage = TopStoriesStorageFactory(context).create()

        return TopStoriesRepoImpl(api, localStorage)
    }
}