package com.example.hyperdesigntask.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hyperdesigntask.home.model.ShippmentResponse
import com.example.hyperdesigntask.home.repo.HomeRepo
import kotlinx.coroutines.launch
import retrofit2.Response


class HomeViewModel(val homeRepo: HomeRepo):ViewModel() {
    private val _shippmentResponse : MutableLiveData<Response<ShippmentResponse>> = MutableLiveData<Response<ShippmentResponse>>()
    val shippmentResponse :LiveData<Response<ShippmentResponse>> = _shippmentResponse

     fun getShipments(token:String){
        viewModelScope.launch {
            val response = homeRepo.getShipments(token)
            _shippmentResponse.value= response
        }
    }



}