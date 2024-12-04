package com.example.hyperdesigntask.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.auth.login.repo.LoginRepo

class LoginViewModelFactory(val loginRepo: LoginRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(loginRepo) as T
        }else{
            throw IllegalArgumentException("login view model class not found")
        }
    }
}