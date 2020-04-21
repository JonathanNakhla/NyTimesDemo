package com.jonathannakhla.nytimesdemo.repositories

import com.jonathannakhla.nytimesdemo.network.TopStoriesApi
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.storage.TopStoriesStorage
import io.reactivex.rxjava3.core.Observable

internal class TopStoriesRepoImpl(private val topStoriesApi: TopStoriesApi,
                                  private val topStoriesStorage: TopStoriesStorage) : TopStoriesRepo {

    companion object {
        private const val API_KEY = "nDiuHSEWcAW4nImAeKvYVi0BxpRfBcmX"
    }

    override fun getTopStories(invalidate: Boolean): Observable<List<Article>> {
        if (invalidate) {
            topStoriesStorage.clearAllStopStories()
        }
        val localStorageTopStories = topStoriesStorage.getTopStories()
        if (localStorageTopStories.isNotEmpty()) {
            return Observable.fromCallable { localStorageTopStories }
        }

        return topStoriesApi.getTopStories(API_KEY)
            .map { it.articles }
            .doOnNext { topStoriesStorage.storeTopStories(it) }
    }

    override fun getArticle(articleId: Int): Article {
        return topStoriesStorage.getArticle(articleId)
    }

    override fun searchTopStories(query: String): Observable<List<Article>> {
        return getTopStories()
            .map {
                it.filter { article ->
                    article.title.contains(query, true)
                            || article.abstract.contains(query, true)
                }
            }
    }
}