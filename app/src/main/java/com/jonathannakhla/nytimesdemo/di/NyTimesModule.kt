package com.jonathannakhla.nytimesdemo.di

import com.jonathannakhla.analytics.track.TrackFactory
import com.jonathannakhla.nytimesdemo.repositories.TopStoriesRepoFactory
import com.jonathannakhla.nytimesdemo.ui.RxSearchView
import com.jonathannakhla.nytimesdemo.viewmodels.DetailsViewModel
import com.jonathannakhla.nytimesdemo.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nyTimesDemoModule = module {
    factory { TopStoriesRepoFactory(androidApplication()).create() }
    factory { RxSearchView() }
}

val analyticsModule = module {
    factory { TrackFactory(androidApplication()).create() }
}

val vmModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get()) }
}