package com.jonathannakhla.nytimesdemo.application

import android.app.Application
import com.jonathannakhla.nytimesdemo.di.analyticsModule
import com.jonathannakhla.nytimesdemo.di.nyTimesDemoModule
import com.jonathannakhla.nytimesdemo.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NyTimesDemoApplication: Application() {
    init {
        startKoin {
            androidContext(this@NyTimesDemoApplication)
            modules(listOf(nyTimesDemoModule, vmModule, analyticsModule))
        }
    }
}