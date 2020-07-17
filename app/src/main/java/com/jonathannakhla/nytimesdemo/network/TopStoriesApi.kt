package com.jonathannakhla.nytimesdemo.network

import com.jonathannakhla.nytimesdemo.data.Results
import io.reactivex.rxjava3.core.Observable

interface TopStoriesApi {
    fun getTopStories(apiKey: String): Observable<Results>
}