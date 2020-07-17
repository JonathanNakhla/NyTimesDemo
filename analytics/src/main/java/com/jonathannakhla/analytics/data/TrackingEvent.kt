package com.jonathannakhla.analytics.data

import kotlinx.serialization.Serializable

@Serializable
data class TrackingEvent(val eventName: String, val properties: Map<String, String>)