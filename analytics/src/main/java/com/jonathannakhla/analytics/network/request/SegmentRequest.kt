package com.jonathannakhla.analytics.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SegmentRequest(
    @SerialName("batch") val event: List<SegmentEvent>
)