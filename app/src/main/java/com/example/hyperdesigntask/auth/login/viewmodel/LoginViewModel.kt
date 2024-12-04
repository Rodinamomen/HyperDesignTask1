package com.example.hyperdesigntask.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hyperdesigntask.auth.login.model.LogInResponse
import com.example.hyperdesigntask.auth.login.repo.LoginRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(val loginRepo: LoginRepo):ViewModel() {
    private val _loginResponse = MutableLiveData<Response<LogInResponse>>()
    val loginResponse: LiveData<Response<LogInResponse>> = _loginResponse
    fun loginUser(phone:String,password:String,token:String){
        viewModelScope.launch {
            val result = loginRepo.loginUser(phone,password,token )
            _loginResponse.value = result
        }
    }
}