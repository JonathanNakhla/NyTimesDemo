package com.jonathannakhla.analytics.workmanager

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.jonathannakhla.analytics.network.api.TrackingEventApiFactory
import com.jonathannakhla.analytics.storage.event.EventStorageFactory
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.Single

class PeriodicUploadWorker(private val context: Context, workerParameters: WorkerParameters) : RxWorker(context, workerParameters) {

    companion object {
        private const val TAG = "UploadWorker"
    }

    override fun createWork(): Single<Result> {

        val eventStorage = EventStorageFactory(context.applicationContext).create()
        val trackingEventApi = TrackingEventApiFactory(context.applicationContext).create()

        return eventStorage.flushAllEvents()
            .filter { it.trackingEvents.isNotEmpty() }
            .flatMapCompletable { trackingEventApi.send(it) }
            .toSingleDefault(Result.success())
            .onErrorReturn { Result.failure() }
            .to(RxJavaBridge.toV2Single())
            .doOnSuccess { Log.d(TAG, "Analytics automatic upload successful!") }
            .doOnError { Log.e(TAG, "Error with analytics automatic upload!") }
    }

}