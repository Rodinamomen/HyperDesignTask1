package com.example.hyperdesigntask.auth.login.repo

import com.example.hyperdesigntask.auth.login.model.LogInResponse
import okhttp3.RequestBody
import retrofit2.Response


interface LoginRepo {
   suspend fun loginUser(  phone : String, password: String,token:String
    ): Response<LogInResponse>
}