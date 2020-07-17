package com.jonathannakhla.analytics.network.service

import com.jonathannakhla.analytics.network.AnalyticsPathConstants
import com.jonathannakhla.analytics.network.request.SegmentRequest
import io.reactivex.rxjava3.core.Completable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TrackingEventService {
    @Headers("Accept: application/json")
    @POST(AnalyticsPathConstants.PATH_ANALYTICS_BATCH_SERVICE)
    fun send(@Body segmentRequest: SegmentRequest, @Header("Authorization") writeKey: String): Completable
}