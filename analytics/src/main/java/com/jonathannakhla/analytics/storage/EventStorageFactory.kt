package com.jonathannakhla.analytics.storage

import android.content.Context
import com.jonathannakhla.analytics.storage.room.EventStorageRoomImpl

class EventStorageFactory(private val context: Context) {
    fun create(): EventStorage {
        return EventStorageRoomImpl(
            context
        )
    }
}