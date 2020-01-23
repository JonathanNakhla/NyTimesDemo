package com.jonathannakhla.nytimesdemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Multimedia(@SerialName("url") val url: String)