package com.jonathannakhla.analytics.track

import android.content.Context
import com.jonathannakhla.analytics.network.api.TrackingEventApiFactory
import com.jonathannakhla.analytics.storage.EventStorageFactory
import com.jonathannakhla.analytics.tracker.PersonalTrackerImpl

class TrackFactory(private val context: Context) {
    fun create(): Trackers {
        val eventStorage = EventStorageFactory(context).create()
        return TrackersImpl(
            PersonalTrackerImpl(
                eventStorage,
                eventStorage.eventStorageChecker,
                TrackingEventApiFactory(context).create()
        ))
    }
}