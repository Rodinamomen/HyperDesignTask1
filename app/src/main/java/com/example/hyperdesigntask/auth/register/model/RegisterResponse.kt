package com.example.hyperdesigntask.auth.register.model

data class RegisterResponse(
    val access_token: String,
    val message: String,
    val status: Int,
    val user: User
)