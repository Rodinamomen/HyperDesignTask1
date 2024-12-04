package com.example.hyperdesigntask.request.repo

import com.example.hyperdesigntask.network.RemoteDataSource
import com.example.hyperdesigntask.request.model.RequestQuotation
import com.example.hyperdesigntask.request.model.RequestResponse
import retrofit2.Response

class RequestRepoImp(val remoteDataSource: RemoteDataSource):RequestRepo {
    override suspend fun requestQuotation(
        token: String,
        requestBody: RequestQuotation
    ): Response<RequestResponse> {
        return remoteDataSource.requestQuotation(token,requestBody)
    }

}