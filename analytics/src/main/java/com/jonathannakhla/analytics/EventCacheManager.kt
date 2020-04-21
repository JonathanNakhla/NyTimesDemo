package com.jonathannakhla.analytics

import android.util.Log
import com.jonathannakhla.analytics.network.service.TrackingEventService
import com.jonathannakhla.analytics.storage.EventStorage
import com.jonathannakhla.analytics.storage.EventStorageChecker
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.network.api.TrackingEventApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface EventCacheManager {
    val eventStorage: EventStorage
    val eventStorageChecker: EventStorageChecker
    val trackingEventApi: TrackingEventApi

    companion object {
        private const val TAG = "EventCacheManager"
    }

    fun storeEvent(trackingEvent: TrackingEvent) {
        eventStorage.storeEvent(trackingEvent)
            .subscribeOn(Schedulers.io())
            .andThen(eventStorageChecker.isBatchReady())
            .filter { it == true }
            .flatMapSingle { eventStorage.flushAllEvents() }
            .flatMapCompletable { trackingEventApi.send(it) }
            .subscribe (
                {Log.d(TAG, "Event storing successful") },
                {Log.e(TAG, "Error storing event", it) }
            )
    }

}