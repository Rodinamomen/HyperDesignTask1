package com.example.hyperdesigntask.details.model

data class ShippmentDetails(
    val Containers: List<Any>,
    val comment: String,
    val description: String,
    val id: Int,
    val shipment_name: String,
    val status: String
)