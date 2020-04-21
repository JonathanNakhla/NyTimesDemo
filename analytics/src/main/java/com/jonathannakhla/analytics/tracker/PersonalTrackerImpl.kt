package com.jonathannakhla.analytics.tracker

import android.util.Log
import androidx.work.*
import com.jonathannakhla.analytics.EventCacheManager
import com.jonathannakhla.analytics.storage.EventStorage
import com.jonathannakhla.analytics.storage.EventStorageChecker
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.network.api.TrackingEventApi
import com.jonathannakhla.analytics.workmanager.PeriodicUploadWorker
import java.util.concurrent.TimeUnit

class PersonalTrackerImpl(
    override val eventStorage: EventStorage,
    override val eventStorageChecker: EventStorageChecker,
    override val trackingEventApi: TrackingEventApi,
    override val workManager: WorkManager
) : Tracker, EventCacheManager {
    override val name: String
        get() = PersonalTrackerImpl::class.java.simpleName

    companion object {
        private const val TAG = "PersonalTrackerImpl"
        private const val WORK_TAG = "PersonalTrackerWork"
        private const val REPEAT_INTERVAL = 15L
    }

    init {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val uploadWorkRequest = PeriodicWorkRequestBuilder<PeriodicUploadWorker>(REPEAT_INTERVAL, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        Log.d(TAG, "Enqueuing job")
        workManager.enqueueUniquePeriodicWork(WORK_TAG, ExistingPeriodicWorkPolicy.REPLACE, uploadWorkRequest)
    }

    override fun track(event: TrackingEvent) {
        storeEvent(event)
    }
}