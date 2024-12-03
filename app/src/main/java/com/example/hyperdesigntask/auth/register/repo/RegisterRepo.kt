package com.example.hyperdesigntask.auth.register.repo

import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface RegisterRepo {
    suspend fun registerUser(name: String,
                             email: String,
                             phone: String,
                             password:String,
                             countryId: String,
                             type: String,
                             file: MultipartBody.Part,
                             token: String) : Response<RegisterResponse>
}