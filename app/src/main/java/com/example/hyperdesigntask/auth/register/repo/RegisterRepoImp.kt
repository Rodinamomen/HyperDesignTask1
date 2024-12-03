package com.example.hyperdesigntask.auth.register.repo

import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import com.example.hyperdesigntask.network.RemoteDataSource
import okhttp3.MultipartBody
import retrofit2.Response

class RegisterRepoImp(var remoteDataSource: RemoteDataSource):RegisterRepo {
    override suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password:String,
        countryId: String,
        type: String,
        file: MultipartBody.Part,
        token: String
    ): Response<RegisterResponse> {
       return remoteDataSource.registerUser(name,email,phone,password,countryId, type, file, token)
    }


}