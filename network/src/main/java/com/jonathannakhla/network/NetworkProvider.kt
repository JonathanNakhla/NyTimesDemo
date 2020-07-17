package com.jonathannakhla.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkProvider(private val baseUrl: String,
                      private val context: Context) {
    fun <T> create(service: Class<T>): T {

        val okHttpClient = OkHttpClient.Builder().addInterceptor(ChuckInterceptor(context.applicationContext)).build()

        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(service)
    }
}