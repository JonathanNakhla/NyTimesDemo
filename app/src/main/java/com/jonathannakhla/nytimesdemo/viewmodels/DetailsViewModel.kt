package com.jonathannakhla.nytimesdemo.viewmodels

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.repositories.TopStoriesRepo

class DetailsViewModel(private val topStoriesRepo: TopStoriesRepo): ViewModel() {

    fun getArticle(articleId: Int): Article {
        return topStoriesRepo.getArticle(articleId)
    }

    fun shareArticle(article: Article, fragment: Fragment) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, article.url)
            putExtra(Intent.EXTRA_TITLE, article.title)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        fragment.startActivity(shareIntent)
    }
}