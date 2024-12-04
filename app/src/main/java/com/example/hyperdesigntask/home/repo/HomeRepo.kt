package com.example.hyperdesigntask.home.repo

import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.home.model.ShippmentResponse
import retrofit2.Response

interface HomeRepo {
    suspend fun getShipments(
        token: String
    ): Response<ShippmentResponse>

}