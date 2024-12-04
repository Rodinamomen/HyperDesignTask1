package com.example.hyperdesigntask.auth.login.repo

import com.example.hyperdesigntask.auth.login.model.LogInResponse
import com.example.hyperdesigntask.network.RemoteDataSource
import okhttp3.RequestBody
import retrofit2.Response

class LoginRepoImp(val remoteDataSource: RemoteDataSource): LoginRepo {
   override suspend fun loginUser(
        phone : String, password: String,token:String
    ): Response<LogInResponse> {
        return  remoteDataSource.loginUser(phone,password,token)
    }

}