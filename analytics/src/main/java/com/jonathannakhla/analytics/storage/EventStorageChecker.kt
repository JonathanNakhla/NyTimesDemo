package com.jonathannakhla.analytics.storage

import io.reactivex.rxjava3.core.Single

interface EventStorageChecker {
    fun isBatchReady(): Single<Boolean>
}