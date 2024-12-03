package com.example.hyperdesigntask.network

import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

object ApiClient:RemoteDataSource {
    private val apiService = ApiHelper.retrofit.create(ApiService::class.java)
    override suspend fun registerUser(name: String,
                                      email: String,
                                      phone: String,
                                      password:String,
                                      countryId: String,
                                      type: String,
                                      file: MultipartBody.Part,
                                      token: String
    ): Response<RegisterResponse> {
        val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val emailPart = RequestBody.create("text/plain".toMediaTypeOrNull(), email)
        val phonePart = RequestBody.create("text/plain".toMediaTypeOrNull(), phone)
        val countryIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), countryId)
        val typePart = RequestBody.create("text/plain".toMediaTypeOrNull(), type)
        val tokenPart = RequestBody.create("text/plain".toMediaTypeOrNull(), token)
        val passwordPart = RequestBody.create("test/plain".toMediaTypeOrNull(),password)
        val response = apiService.registerUser(namePart, emailPart, phonePart, passwordPart,countryIdPart, typePart, file, tokenPart)
        return  response
    }

  /*  override suspend fun refreshToken(authHeader: String, id: String): Response<RegisterResponse> {
         val response = apiService.refreshToken(authHeader,id)
        return  response
    }*/


}