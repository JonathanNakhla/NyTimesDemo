package com.jonathannakhla.nytimesdemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(@SerialName("title") val title: String,
                   @SerialName("abstract") val abstract: String,
                   @SerialName("url") val url: String,
                   @SerialName("created_date") val date: String,
                   @SerialName("multimedia") val multimedias: List<Multimedia>?)