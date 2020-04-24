package com.jonathannakhla.analytics.storage.event

import io.reactivex.rxjava3.core.Single

interface EventStorageChecker {
    fun isBatchReady(): Single<Boolean>
}