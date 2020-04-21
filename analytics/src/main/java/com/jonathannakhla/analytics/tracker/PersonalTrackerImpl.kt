package com.jonathannakhla.analytics.tracker

import com.jonathannakhla.analytics.EventCacheManager
import com.jonathannakhla.analytics.network.service.TrackingEventService
import com.jonathannakhla.analytics.storage.EventStorage
import com.jonathannakhla.analytics.storage.EventStorageChecker
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.network.api.TrackingEventApi

class PersonalTrackerImpl(
    override val eventStorage: EventStorage,
    override val eventStorageChecker: EventStorageChecker,
    override val trackingEventApi: TrackingEventApi
) : Tracker, EventCacheManager {
    override val name: String
        get() = PersonalTrackerImpl::class.java.simpleName

    override fun track(event: TrackingEvent) {
        storeEvent(event)
    }
}