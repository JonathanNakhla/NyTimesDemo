package com.jonathannakhla.analytics.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackingEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "event_name") val eventName: String,
    @ColumnInfo(name = "properties") val properties: String
)