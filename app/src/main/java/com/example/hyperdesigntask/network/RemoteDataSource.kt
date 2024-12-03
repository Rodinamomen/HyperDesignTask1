package com.example.hyperdesigntask.network

import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface RemoteDataSource {
    suspend fun registerUser(name: String,
                             email: String,
                             phone: String,
                             password:String,
                             countryId: String,
                             type: String,
                             file: MultipartBody.Part,
                             token: String) : Response<RegisterResponse>
  //  suspend fun refreshToken(authHeader: String, id:String):Response<RegisterResponse>
}