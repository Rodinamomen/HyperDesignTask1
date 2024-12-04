package com.example.hyperdesigntask.request.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hyperdesigntask.request.model.RequestQuotation

import com.example.hyperdesigntask.request.model.RequestResponse
import com.example.hyperdesigntask.request.repo.RequestRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class RequestViewModel(val requestRepo: RequestRepo):ViewModel(){
    private val _requestResponse : MutableLiveData<Response<RequestResponse>> = MutableLiveData<Response<RequestResponse>>()
    val requestResponse : LiveData<Response<RequestResponse>> = _requestResponse
    fun requestQuotation(
        token: String,
        requestBody: RequestQuotation
    ){
        viewModelScope.launch {
            val response = requestRepo.requestQuotation(token,requestBody)
            _requestResponse.value = response
        }
    }
}