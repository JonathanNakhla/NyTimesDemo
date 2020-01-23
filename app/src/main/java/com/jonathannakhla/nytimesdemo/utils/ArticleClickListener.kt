package com.jonathannakhla.nytimesdemo.utils

import android.view.View
import com.jonathannakhla.nytimesdemo.data.Article

interface ArticleClickListener {
    fun onArticleClick(article: Article, sharedImage: View, sharedText: View)
}