package com.jonathannakhla.analytics.tracker

import com.jonathannakhla.analytics.data.TrackingEvent

interface Tracker {
    val name: String

    fun track(event: TrackingEvent)
}