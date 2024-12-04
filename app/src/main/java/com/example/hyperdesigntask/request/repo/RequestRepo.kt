package com.example.hyperdesigntask.request.repo

import com.example.hyperdesigntask.request.model.RequestQuotation
import com.example.hyperdesigntask.request.model.RequestResponse
import retrofit2.Response

interface RequestRepo {
    suspend fun requestQuotation(
        token: String,
        requestBody: RequestQuotation
    ): Response<RequestResponse>
}