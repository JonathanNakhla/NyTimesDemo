package com.jonathannakhla.analytics.network.api

import com.jonathannakhla.analytics.data.TrackingEventBatch
import io.reactivex.rxjava3.core.Completable

interface TrackingEventApi {
    fun send(eventBatch: TrackingEventBatch): Completable
}