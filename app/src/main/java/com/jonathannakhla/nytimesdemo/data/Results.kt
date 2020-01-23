package com.jonathannakhla.nytimesdemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Results(@SerialName("results") val articles: List<Article>)