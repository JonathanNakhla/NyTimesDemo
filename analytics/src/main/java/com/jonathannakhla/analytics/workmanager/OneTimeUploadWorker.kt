package com.jonathannakhla.analytics.workmanager

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.jonathannakhla.analytics.deserializeBatch
import com.jonathannakhla.analytics.network.api.TrackingEventApiFactory
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Single

class OneTimeUploadWorker(private val context: Context, workerParameters: WorkerParameters) : RxWorker(context, workerParameters) {

    companion object {
        private const val TAG = "UploadWorker"
        const val SERIALIZED_BATCH_KEY = "serialized_batch"
    }

    override fun createWork(): io.reactivex.Single<Result> {

        val serializedBatch = inputData.getString(SERIALIZED_BATCH_KEY)
        val trackingEventBatch = serializedBatch?.deserializeBatch()
            ?: return Single.fromCallable { Result.success() }
                .to(RxJavaBridge.toV2Single())

        val trackingEventApi = TrackingEventApiFactory(context.applicationContext).create()

        return Single.fromCallable { trackingEventBatch }
            .flatMapCompletable { trackingEventApi.send(it) }
            .toSingle { Result.success() }
            .onErrorReturn { Result.failure() }
            .to(RxJavaBridge.toV2Single())
            .doOnSuccess { Log.d(TAG, "Analytics upload successful!") }
            .doOnError { Log.e(TAG, "Error with analytics upload!") }
    }

}