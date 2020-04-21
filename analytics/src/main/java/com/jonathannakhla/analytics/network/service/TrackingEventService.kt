package com.jonathannakhla.analytics.network.service

import com.jonathannakhla.analytics.request.SegmentRequest
import io.reactivex.rxjava3.core.Completable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TrackingEventService {
    @Headers("Accept: application/json")
    @POST("v1/batch")
    fun send(@Body segmentRequest: SegmentRequest, @Header("Authorization") writeKey: String): Completable
}