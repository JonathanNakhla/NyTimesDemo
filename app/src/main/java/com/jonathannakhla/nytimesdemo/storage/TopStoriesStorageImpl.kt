package com.jonathannakhla.nytimesdemo.storage

import android.content.SharedPreferences
import com.jonathannakhla.nytimesdemo.data.Article
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

internal class TopStoriesStorageImpl(private val sharedPreferences: SharedPreferences) : TopStoriesStorage {

    companion object {
        private const val ARTICLES_KEY = "articles_key"
    }

    private val json by lazy {
        Json(JsonConfiguration.Stable)
    }

    override fun getTopStories(): List<Article> {
        val serializedArticles = sharedPreferences.getString(ARTICLES_KEY, "")
        if (serializedArticles.isNullOrBlank()) {
            return emptyList()
        }

        return json.parse(Article.serializer().list, serializedArticles)
    }

    override fun storeTopStories(topStories: List<Article>) {
        if (topStories.isEmpty()) {
            return
        }

        sharedPreferences.edit().putString(ARTICLES_KEY, json.stringify(Article.serializer().list, topStories)).apply()
    }

    override fun clearAllStopStories() {
        sharedPreferences.edit().clear().apply()
    }

    override fun getArticle(articleId: Int): Article {
        val topStories = getTopStories()
        topStories.forEach {
            if (it.hashCode() == articleId) {
                return it
            }
        }
        throw IllegalArgumentException("Unable to find an article with id $articleId")
    }
}