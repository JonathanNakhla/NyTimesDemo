package com.jonathannakhla.nytimesdemo.utils

import com.jonathannakhla.nytimesdemo.data.Article

interface ViewArticleRowCallback {
    fun onViewArticleRow(article: Article)
}