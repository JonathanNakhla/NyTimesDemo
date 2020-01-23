package com.jonathannakhla.nytimesdemo.api

import com.jonathannakhla.nytimesdemo.data.Results
import io.reactivex.rxjava3.core.Observable

internal class TopStoriesApiImpl(private val service: TopStoriesService) : TopStoriesApi {
    override fun getTopStories(apiKey: String): Observable<Results> {
        return service.topStories(apiKey)
    }
}