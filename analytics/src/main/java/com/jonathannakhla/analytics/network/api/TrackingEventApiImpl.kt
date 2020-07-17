package com.jonathannakhla.analytics.network.api

import android.util.Base64
import com.jonathannakhla.analytics.data.TrackingEventBatch
import com.jonathannakhla.analytics.network.service.TrackingEventService
import com.jonathannakhla.analytics.storage.user.UserManager
import com.jonathannakhla.analytics.toSegmentRequest
import io.reactivex.rxjava3.core.Completable

class TrackingEventApiImpl(private val serviceTracking: TrackingEventService,
                           private val userManager: UserManager) : TrackingEventApi {

    companion object {
        private const val WRITE_KEY = "RJv6L6uGP7UdB8BMw0gj5VdlnnP6HYAm" + ":"
    }

    @ExperimentalStdlibApi
    override fun send(eventBatch: TrackingEventBatch): Completable {
        val segmentRequest = eventBatch.toSegmentRequest(userManager.getUserId())
        return serviceTracking.send(segmentRequest, "Basic ${encodeWriteKey()}")
    }

    @ExperimentalStdlibApi
    private fun encodeWriteKey(): String {
        val data: ByteArray = WRITE_KEY.encodeToByteArray()
        return Base64.encodeToString(data, Base64.NO_WRAP)
    }
}