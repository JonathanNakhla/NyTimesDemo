package com.jonathannakhla.analytics.data

import kotlinx.serialization.Serializable

@Serializable
data class TrackingEventBatch(val trackingEvents: List<TrackingEvent>) // TODO common properties