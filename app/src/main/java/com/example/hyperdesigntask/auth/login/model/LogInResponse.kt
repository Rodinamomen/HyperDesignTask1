package com.example.hyperdesigntask.auth.login.model

data class LogInResponse(
    val access_token: String,
    val message: String,
    val status: Int,
    val user: User
)