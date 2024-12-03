package com.example.hyperdesigntask.network

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.GsonBuilder


import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@SuppressLint("StaticFieldLeak")
object ApiHelper {


  //  val sharedPreferences = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE)
   // val token = sharedPreferences.getString("token", null)
    val gson = GsonBuilder().serializeNulls().create()
    private val loggingInterceptor = Interceptor()
  //  private val refreshTokenInterceptor = RefreshTokenInterceptor(, sharedPreferences)


    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.hyper-design.com/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}