package com.jonathannakhla.analytics.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TrackingEventEntity::class], version = 1)
abstract class TrackingEventDatabase : RoomDatabase() {
    abstract fun trackingEventDao(): TrackingEventDao

    companion object {
        private const val DATABASE_NAME = "analytics"

        @Volatile private var instance: TrackingEventDatabase? = null

        fun getInstance(context: Context): TrackingEventDatabase {
            return instance?: synchronized(this) {
                instance ?: makeTrackingEventDatabase(context)
                    .also { instance = it }
            }
        }

        private fun makeTrackingEventDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TrackingEventDatabase::class.java,
                DATABASE_NAME
            ).build()
    }


}