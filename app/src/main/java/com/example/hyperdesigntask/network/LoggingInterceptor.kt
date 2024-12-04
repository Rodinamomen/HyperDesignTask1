package com.example.hyperdesigntask.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("NetworkRequest", "Sending request to URL: ${request.url}")
        Log.d("NetworkRequest", "Headers: ${request.body}")
        return chain.proceed(request)
    }

    }
