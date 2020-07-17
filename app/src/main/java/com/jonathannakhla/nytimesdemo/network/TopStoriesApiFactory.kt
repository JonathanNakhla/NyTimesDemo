package com.jonathannakhla.nytimesdemo.network

import android.content.Context
import com.jonathannakhla.network.NetworkProvider

class TopStoriesApiFactory(private val context: Context) {

    companion object {
        private const val NYTIMES_BASE_URL = "https://api.nytimes.com"
    }

    fun create(): TopStoriesApi {
        val networkProvider = NetworkProvider(NYTIMES_BASE_URL, context)
        val service = networkProvider.create(TopStoriesService::class.java)

        return TopStoriesApiImpl(service)
    }
}