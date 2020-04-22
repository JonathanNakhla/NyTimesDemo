package com.jonathannakhla.analytics.storage

import android.content.Context
import com.jonathannakhla.analytics.storage.room.EventStorageRoomImpl
import com.jonathannakhla.analytics.storage.room.TrackingEventDatabase

class EventStorageFactory(private val context: Context) {
    fun create(): EventStorage {
        return EventStorageRoomImpl(TrackingEventDatabase.getInstance(context))
    }
}