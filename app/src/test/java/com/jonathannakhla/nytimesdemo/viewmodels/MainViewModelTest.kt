package com.jonathannakhla.nytimesdemo.viewmodels

import androidx.appcompat.widget.SearchView
import com.jonathannakhla.analytics.track.Trackers
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.repositories.TopStoriesRepo
import com.jonathannakhla.nytimesdemo.ui.RxSearchView
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class MainViewModelTest {

    @RelaxedMockK private lateinit var topStoriesRepo: TopStoriesRepo
    @RelaxedMockK private lateinit var searchViewRx: RxSearchView
    @RelaxedMockK private lateinit var trackers: Trackers

    private lateinit var mainViewModel: MainViewModel

    @BeforeEach
    fun setUp() {
        mainViewModel = MainViewModel(
            topStoriesRepo,
            searchViewRx,
            trackers
        )
    }

    @Test
    fun `test search`() {
        val searchView = mockk<SearchView>()
        val query = "Tra"
        val queryObservable = Observable.just(query)

        val articleOne = Article("someTitleOne", "someAbstractOne", "someUrlOne", "someDateOne", null)
        val articleTwo = Article("someTitleTwo", "someAbstractTwo", "someUrlTwo", "someDateOne", null)

        val topStories = listOf(articleOne, articleTwo)

        every { searchViewRx.getObservable(searchView) } returns queryObservable
        every { topStoriesRepo.searchTopStories(query) } returns Observable.fromCallable { topStories }

        mainViewModel.getSearchObservable(searchView)
            .test()
            .assertValue(topStories)
    }

}