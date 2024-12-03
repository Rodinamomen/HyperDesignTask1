package com.example.hyperdesigntask.auth.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.auth.register.repo.RegisterRepo

class RegisterViewModelFactory(val registerRepo: RegisterRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            RegisterViewModel(registerRepo) as T
        }else{
            throw IllegalArgumentException("register view model class not found")
        }
    }
}
