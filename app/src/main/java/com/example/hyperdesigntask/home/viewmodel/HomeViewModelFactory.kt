package com.example.hyperdesigntask.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.auth.login.viewmodel.LoginViewModel
import com.example.hyperdesigntask.home.repo.HomeRepo

class HomeViewModelFactory(val homeRepo: HomeRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(homeRepo) as T
        }else{
            throw IllegalArgumentException("Home view model class not found")
        }
    }
}