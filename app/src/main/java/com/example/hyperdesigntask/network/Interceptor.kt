package com.example.hyperdesigntask.network

import android.util.Log
import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

 class Interceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("NetworkRequest", "Sending request to URL: ${request.url}")
        return chain.proceed(request)
    }

    }
