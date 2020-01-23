package com.jonathannakhla.nytimesdemo.storage

import com.jonathannakhla.nytimesdemo.data.Article

interface TopStoriesStorage {
    fun getTopStories(): List<Article>
    fun storeTopStories(topStories: List<Article>)
    fun clearAllStopStories()
    fun getArticle(articleId: Int): Article
}