package com.example.hyperdesigntask.auth.login.model

data class User(
    val active: String,
    val admin: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val type: String,
    val updated_at: String
)