package com.example.hyperdesigntask.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hyperdesigntask.details.model.ShippmentDetailsResponse
import com.example.hyperdesigntask.details.repo.DetailsRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(val detailsRepo: DetailsRepo):ViewModel() {
    private val _shippmentDetailsResponse : MutableLiveData<Response<ShippmentDetailsResponse>> = MutableLiveData<Response<ShippmentDetailsResponse>>()

    val shippmentDetailsResponse : LiveData<Response<ShippmentDetailsResponse>> = _shippmentDetailsResponse
    fun getShippmentDetailsResponse(token: String,id:Int){
        viewModelScope.launch {
            val response = detailsRepo.getShipmentsDetails(token,id)
            _shippmentDetailsResponse.value = response
        }
    }
}