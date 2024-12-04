package com.example.hyperdesigntask.network

import com.example.hyperdesigntask.auth.login.model.LogInResponse
import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.home.model.ShippmentResponse
import com.example.hyperdesigntask.request.model.RequestQuotation
import com.example.hyperdesigntask.request.model.RequestResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header


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
  suspend fun requestQuotation(
      token: String,
      requestBody: RequestQuotation
  ): Response<RequestResponse>
  suspend fun loginUser( phone : String, password: String,token: String
  ):Response<LogInResponse>
    suspend fun getShipments(
         token: String
    ): Response<ShippmentResponse>
    suspend fun getShipmentsDetails(token:String, id:Int):Response<ShippmentDetailsResponse>
}