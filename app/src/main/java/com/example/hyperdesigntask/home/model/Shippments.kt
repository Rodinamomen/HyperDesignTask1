package com.example.hyperdesigntask.home.model

data class Shippments(
    val current_page: Int,
    val `data`: List<Data>,
    val from: Int,
    val last_page: Int,
    val next_page_url: Any,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)