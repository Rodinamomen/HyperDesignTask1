package com.example.hyperdesigntask.details.repo

import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import retrofit2.Response

interface DetailsRepo {
    suspend fun getShipmentsDetails(token:String,id:Int): Response<ShippmentDetailsResponse>
}