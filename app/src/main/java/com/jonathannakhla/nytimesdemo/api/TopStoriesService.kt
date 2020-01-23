package com.jonathannakhla.nytimesdemo.api

import com.jonathannakhla.nytimesdemo.data.Results
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopStoriesService {
    @GET("svc/topstories/v2/world.json")
    fun topStories(@Query("api-key") apiKey: String): Observable<Results>
}