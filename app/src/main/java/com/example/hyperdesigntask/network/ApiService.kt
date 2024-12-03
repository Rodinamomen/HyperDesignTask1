package com.example.hyperdesigntask.network

import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ApiService {
    @Multipart
    @POST("register")
    suspend fun registerUser(
        @Part("name") name :RequestBody,
        @Part("email") email:RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password:RequestBody,
        @Part("country_id") country_id: RequestBody,
        @Part("type") type : RequestBody,
        @Part file : MultipartBody.Part,
        @Part("token") token : RequestBody
    ):Response<RegisterResponse>

  /*  @POST("refresh")
    suspend fun refreshToken(  @Header("Authorization") authHeader: String,
                               @Body() id: String
    ):Response<RegisterResponse>*/
}