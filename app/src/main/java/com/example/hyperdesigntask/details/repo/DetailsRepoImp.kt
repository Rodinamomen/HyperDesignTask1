package com.example.hyperdesigntask.details.repo

import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.network.RemoteDataSource
import retrofit2.Response

class DetailsRepoImp(val remoteDataSource: RemoteDataSource):DetailsRepo {
    override suspend fun getShipmentsDetails(
        token: String,
        id: Int
    ): Response<ShippmentDetailsResponse> {
        return remoteDataSource.getShipmentsDetails(token,id)
    }

}