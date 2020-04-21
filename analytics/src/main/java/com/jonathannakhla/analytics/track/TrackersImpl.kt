package com.jonathannakhla.analytics.track

import com.jonathannakhla.analytics.tracker.Tracker
import com.jonathannakhla.analytics.data.TrackingEvent

class TrackersImpl(private vararg val trackers: Tracker): Trackers {
    override fun track(event: TrackingEvent) {
        for (tracker in trackers) {
            tracker.track(event)
        }
    }
}