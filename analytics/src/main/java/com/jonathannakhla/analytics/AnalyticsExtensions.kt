package com.jonathannakhla.analytics

import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.data.TrackingEventBatch
import com.jonathannakhla.analytics.network.request.SegmentEvent
import com.jonathannakhla.analytics.network.request.SegmentRequest
import com.jonathannakhla.analytics.storage.room.TrackingEventEntity
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.parseMap
import kotlinx.serialization.stringify


@UseExperimental(ImplicitReflectionSerializer::class)
fun TrackingEventBatch.serialize() =
    Json(JsonConfiguration.Stable).stringify(TrackingEventBatch.serializer(), this)

fun String.deserializeBatch() =
    Json(JsonConfiguration.Stable).parse(TrackingEventBatch.serializer(),this)

@UseExperimental(ImplicitReflectionSerializer::class)
fun TrackingEvent.toTrackingEventEntity() =
    TrackingEventEntity(
        eventName = this.eventName,
        properties = Json(JsonConfiguration.Stable).stringify(this.properties)
    )

@UseExperimental(ImplicitReflectionSerializer::class)
fun TrackingEventEntity.toTrackingEvent() =
    TrackingEvent(eventName = this.eventName, properties = Json(JsonConfiguration.Stable).parseMap(this.properties))

fun TrackingEvent.toSegmentEvent(userId: String) =
    SegmentEvent(
        type = SegmentType.TRACK.value,
        eventName = this.eventName,
        properties = this.properties,
        userId = userId
    )

fun TrackingEventBatch.toSegmentRequest(userId: String): SegmentRequest {
    val batch = this.trackingEvents.map {
        it.toSegmentEvent(userId)
    }
    return SegmentRequest(batch)
}
