package com.example.hyperdesigntask.network
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiHelper {
  private val loggingInterceptor = LoggingInterceptor()
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(interceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val gson = GsonBuilder().serializeNulls().create()
    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://www.hyper-design.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}