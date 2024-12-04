package com.example.hyperdesigntask.request.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.home.viewmodel.HomeViewModel
import com.example.hyperdesigntask.request.repo.RequestRepo

class RequestViewModelFactory(val requestRepo: RequestRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RequestViewModel::class.java)) {
            RequestViewModel(requestRepo) as T
        }else{
            throw IllegalArgumentException("request view model class not found")
        }
    }
}