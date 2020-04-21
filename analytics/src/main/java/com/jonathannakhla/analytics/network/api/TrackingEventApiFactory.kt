package com.jonathannakhla.analytics.network.api

import android.content.Context
import com.jonathannakhla.analytics.network.service.TrackingEventService
import com.jonathannakhla.network.NetworkProvider

class TrackingEventApiFactory(private val context: Context) {

    companion object {
        private const val SEGMENT_BASE_URL = "https://api.segment.io/"
    }

    fun create(): TrackingEventApi {
        val networkProvider = NetworkProvider(SEGMENT_BASE_URL, context.applicationContext)
        val service = networkProvider.create(TrackingEventService::class.java)
        return TrackingEventApiImpl(service)
    }
}