package com.jonathannakhla.analytics.storage

import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.data.TrackingEventBatch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface EventStorage {

    val eventStorageChecker: EventStorageChecker

    fun storeEvent(event: TrackingEvent): Completable // TODO common properties

    fun flushAllEvents(): Single<TrackingEventBatch>
}