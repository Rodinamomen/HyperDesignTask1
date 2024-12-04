package com.example.hyperdesigntask.network
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiHelper {
  private val loggingInterceptor = LoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val gson = GsonBuilder().serializeNulls().create()
    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://www.hyper-design.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}