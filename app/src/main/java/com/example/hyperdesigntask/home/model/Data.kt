package com.example.hyperdesigntask.home.model

data class Data(
    val Containers: List<Container>,
    val comment: String,
    val description: String,
    val id: Int,
    val shipment_name: String,
    val status: String
)