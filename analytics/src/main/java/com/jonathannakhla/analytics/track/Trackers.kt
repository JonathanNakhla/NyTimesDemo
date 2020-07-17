package com.jonathannakhla.analytics.track

import com.jonathannakhla.analytics.data.TrackingEvent

interface Trackers {
    fun track(event: TrackingEvent)
}