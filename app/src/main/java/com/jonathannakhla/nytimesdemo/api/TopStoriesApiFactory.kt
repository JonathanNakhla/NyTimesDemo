package com.jonathannakhla.nytimesdemo.api

class TopStoriesApiFactory {

    fun create(): TopStoriesApi {
        val networkProvider = NetworkProvider("https://api.nytimes.com")
        val service = networkProvider.create(TopStoriesService::class.java)

        return TopStoriesApiImpl(service)
    }
}