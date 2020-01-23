package com.jonathannakhla.nytimesdemo.repositories

import com.jonathannakhla.nytimesdemo.data.Article
import io.reactivex.rxjava3.core.Observable

interface TopStoriesRepo {
    fun getTopStories(invalidate: Boolean = false): Observable<List<Article>>
    fun getArticle(articleId: Int): Article
    fun searchTopStories(query: String): Observable<List<Article>>
}