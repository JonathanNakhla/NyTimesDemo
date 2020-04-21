package com.jonathannakhla.analytics.storage.room

import android.content.Context
import androidx.room.Room
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.data.TrackingEventBatch
import com.jonathannakhla.analytics.storage.EventStorage
import com.jonathannakhla.analytics.storage.EventStorageChecker
import com.jonathannakhla.analytics.toTrackingEvent
import com.jonathannakhla.analytics.toTrackingEventEntity
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class EventStorageRoomImpl(context: Context) :
    EventStorage {

    companion object {
        private const val DATABASE_NAME = "analytics"
        private const val BATCH_COUNT = 15
    }

    private val db = Room.databaseBuilder(
        context.applicationContext,
        TrackingEventDatabase::class.java,
        DATABASE_NAME
    ).build()

    override fun storeEvent(event: TrackingEvent): Completable {
        val eventEntity = event.toTrackingEventEntity()
        return db.trackingEventDao().insert(eventEntity)
            .`as`(RxJavaBridge.toV3Completable())
    }

    override fun flushAllEvents(): Single<TrackingEventBatch> {
        return db.trackingEventDao().getAll()
            .`as`(RxJavaBridge.toV3Single())
            .map {
                db.trackingEventDao().delete(*it.toTypedArray())
                it
            }.map {
                TrackingEventBatch(it.map { entity -> entity.toTrackingEvent() })
            }
    }

    override val eventStorageChecker = object :
        EventStorageChecker {
        override fun isBatchReady() = db.trackingEventDao().getCount()
            .`as`(RxJavaBridge.toV3Single())
            .map { it >= BATCH_COUNT }
    }

}