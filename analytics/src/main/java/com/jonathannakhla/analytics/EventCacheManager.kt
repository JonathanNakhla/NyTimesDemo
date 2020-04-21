package com.jonathannakhla.analytics

import android.util.Log
import androidx.work.*
import com.jonathannakhla.analytics.storage.EventStorage
import com.jonathannakhla.analytics.storage.EventStorageChecker
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.network.api.TrackingEventApi
import com.jonathannakhla.analytics.workmanager.OneTimeUploadWorker
import io.reactivex.rxjava3.schedulers.Schedulers

interface EventCacheManager {
    val eventStorage: EventStorage
    val eventStorageChecker: EventStorageChecker
    val trackingEventApi: TrackingEventApi
    val workManager: WorkManager

    companion object {
        private const val TAG = "EventCacheManager"
    }

    fun storeEvent(trackingEvent: TrackingEvent) {
        eventStorage.storeEvent(trackingEvent)
            .subscribeOn(Schedulers.io())
            .andThen(eventStorageChecker.isBatchReady())
            .filter { it == true }
            .flatMapSingle { eventStorage.flushAllEvents() }
            .map {
                val serializedBatch = it.serialize()
                val data = Data.Builder()
                    .putString(OneTimeUploadWorker.SERIALIZED_BATCH_KEY, serializedBatch)
                    .build()
                val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                val uploadWorkRequest = OneTimeWorkRequestBuilder<OneTimeUploadWorker>()
                    .setConstraints(constraints)
                    .setInputData(data)
                    .build()
                Log.d(TAG, "Enqueuing job")
                workManager.enqueueUniqueWork(serializedBatch.hashCode().toString(), ExistingWorkPolicy.APPEND, uploadWorkRequest)
            }
            .subscribe (
                { Log.d(TAG, "Event storing successful") },
                { Log.e(TAG, "Error storing event", it) }
            )
    }

}