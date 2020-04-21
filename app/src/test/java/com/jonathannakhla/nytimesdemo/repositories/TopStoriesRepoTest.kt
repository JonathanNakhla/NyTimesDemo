package com.jonathannakhla.nytimesdemo.repositories

import com.jonathannakhla.nytimesdemo.network.TopStoriesApi
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.data.Results
import com.jonathannakhla.nytimesdemo.storage.TopStoriesStorage
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TopStoriesRepoTest {

    @RelaxedMockK private lateinit var topStoriesApi: TopStoriesApi
    @RelaxedMockK private lateinit var topStoriesStorage: TopStoriesStorage

    private lateinit var topStoriesRepo: TopStoriesRepo

    @BeforeEach
    fun setUp() {
        topStoriesRepo = TopStoriesRepoImpl(topStoriesApi, topStoriesStorage)
    }

    @Test
    fun `test searchTopStories title local`() {
        val articleOne = Article("someTitleOne", "someAbstractOne", "someUrlOne", null)
        val articleTwo = Article("someTitleTwo", "someAbstractTwo", "someUrlTwo", null)
        val topStories = listOf(articleOne, articleTwo)

        every { topStoriesStorage.getTopStories() } returns topStories

        val query = "sOmeTitleO"

        topStoriesRepo.searchTopStories(query)
            .test()
            .assertValue {
                it.size == 1 && it[0] == articleOne
            }
    }

    @Test
    fun `test searchTopStories abstract local`() {
        val articleOne = Article("someTitleOne", "someAbstractOne", "someUrlOne", null)
        val articleTwo = Article("someTitleTwo", "someAbstractTwo", "someUrlTwo", null)
        val topStories = listOf(articleOne, articleTwo)

        every { topStoriesStorage.getTopStories() } returns topStories

        val query = "sOMeabstractTW"

        topStoriesRepo.searchTopStories(query)
            .test()
            .assertValue {
                it.size == 1 && it[0] == articleTwo
            }
    }

    @Test
    fun `test searchTopStories title api`() {
        val articleOne = Article("someTitleOne", "someAbstractOne", "someUrlOne", null)
        val articleTwo = Article("someTitleTwo", "someAbstractTwo", "someUrlTwo", null)
        val topStories = listOf(articleOne, articleTwo)

        every { topStoriesStorage.getTopStories() } returns emptyList()
        every { topStoriesApi.getTopStories(any()) } returns Observable.fromCallable { Results(topStories) }

        val query = "sOmeTitleO"

        topStoriesRepo.searchTopStories(query)
            .test()
            .assertValue {
                it.size == 1 && it[0] == articleOne
            }
    }
}