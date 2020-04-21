package com.jonathannakhla.analytics.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrackingEventEntity::class], version = 1)
abstract class TrackingEventDatabase : RoomDatabase() {
    abstract fun trackingEventDao(): TrackingEventDao
}