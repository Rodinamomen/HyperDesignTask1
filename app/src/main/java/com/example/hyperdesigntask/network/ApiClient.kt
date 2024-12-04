package com.example.hyperdesigntask.network

import com.example.hyperdesigntask.auth.login.model.LogInResponse
import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.home.model.ShippmentResponse
import com.example.hyperdesigntask.request.model.RequestQuotation
import com.example.hyperdesigntask.request.model.RequestResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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
        val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val phonePart = phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val countryIdPart = countryId.toRequestBody("text/plain".toMediaTypeOrNull())
        val typePart = type.toRequestBody("text/plain".toMediaTypeOrNull())
        val tokenPart = token.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordPart = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val response = apiService.registerUser(namePart, emailPart, phonePart, passwordPart,countryIdPart, typePart, file, tokenPart)
        return  response
    }

    override suspend fun requestQuotation(
        token: String,
        requestBody: RequestQuotation
    ): Response<RequestResponse> {

        val bearerToken = "Bearer $token"
        return  apiService.requestQuotation(bearerToken,requestBody)
    }

    override suspend fun loginUser(
        phone : String, password: String, token: String
    ): Response<LogInResponse> {
        val phonePart = RequestBody.create("text/plain".toMediaTypeOrNull(), phone)
        val passwordPart = RequestBody.create("text/plain".toMediaTypeOrNull(),password)
        val tokenPart =RequestBody.create("text/plain".toMediaTypeOrNull(),token)
        val response = apiService.loginUser(phonePart,passwordPart,tokenPart)
        return  response
    }

    override suspend fun getShipments(token: String): Response<ShippmentResponse> {
        val bearerToken = "Bearer $token"
        return  apiService.getShipments(bearerToken)
    }

    override suspend fun getShipmentsDetails(
        token: String,
        id: Int
    ): Response<ShippmentDetailsResponse> {

        val bearerToken = "Bearer $token"
        return apiService.getShipmentsDetails(bearerToken,id)
    }

    /*  override suspend fun refreshToken(authHeader: String, id: String): Response<RegisterResponse> {
           val response = apiService.refreshToken(authHeader,id)
          return  response
      }*/


}