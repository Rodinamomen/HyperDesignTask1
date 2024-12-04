package com.example.hyperdesigntask.request.model



data class RequestQuotation(val shipment_name: String,
                            val description: String,
                            val quantity: String,
                            val containerNumber: List<RequestContainer>,
                            val comment: String)
