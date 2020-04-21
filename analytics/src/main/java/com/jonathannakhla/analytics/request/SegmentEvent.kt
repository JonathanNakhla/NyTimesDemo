package com.jonathannakhla.analytics.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SegmentEvent(
    @SerialName("type") val type: String,
    @SerialName("event") val eventName: String,
    @SerialName("properties") val properties: Map<String, String>,
    @SerialName("anonymousId") val userId: String)