package com.example.hyperdesigntask.network

import android.content.SharedPreferences
import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import okhttp3.Interceptor
import retrofit2.Response

/*class RefreshTokenInterceptor(val apiService: ApiService,private val sharedPreferences: SharedPreferences) :

    Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        var token = sharedPreferences.getString("token", null)
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        var response = chain.proceed(request)
        if (response.code == 401) {

            val refreshResponse = runBlocking(Dispatchers.IO){refreshAccessToken()}
            if (refreshResponse != null && refreshResponse.isSuccessful) {
                val newToken = refreshResponse.body()?.access_token
                sharedPreferences.edit().putString("token", newToken).apply()
                request = request.newBuilder()
                    .addHeader("Authorization", "Bearer $newToken")
                    .build()

                response = chain.proceed(request)
            }
        }

        return response
    }
  /*  private suspend fun refreshAccessToken(): retrofit2.Response<RegisterResponse> {

            val refreshResponse = apiService.refreshToken("Bearer ${sharedPreferences.getString("token",null)}",
                sharedPreferences.getString("id",null).toString()
            )
            return refreshResponse
    }*/
    }

*/