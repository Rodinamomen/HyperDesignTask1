package com.example.hyperdesigntask.home.repo

import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.home.model.ShippmentResponse
import com.example.hyperdesigntask.network.RemoteDataSource
import retrofit2.Response

class HomeRepoImp(val remoteDataSource: RemoteDataSource):HomeRepo {
    override suspend fun getShipments(token: String): Response<ShippmentResponse> {
        return remoteDataSource.getShipments(token)
    }

}