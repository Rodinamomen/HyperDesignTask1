package com.example.hyperdesigntask.auth.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hyperdesigntask.auth.register.model.RegisterResponse
import com.example.hyperdesigntask.auth.register.repo.RegisterRepo
import com.example.hyperdesigntask.network.RemoteDataSource
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response

class RegisterViewModel(val registerRepo: RegisterRepo):ViewModel() {
    private val _registerResponse = MutableLiveData<Response<RegisterResponse>>()
    val registerResponse: LiveData<Response<RegisterResponse>> = _registerResponse
    fun registerUser(name: String,email: String, phone: String, password:String,countryId: String, type: String, file: MultipartBody.Part, token: String) {
        viewModelScope.launch {
            _registerResponse.value = registerRepo.registerUser(name,email,phone,password,countryId,type,file,token)
        }
    }
}