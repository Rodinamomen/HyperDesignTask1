package com.example.hyperdesigntask.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.details.repo.DetailsRepo

class DetailsViewModelFactory(val detailsRepo: DetailsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            DetailsViewModel(detailsRepo) as T
        }else{
            throw IllegalArgumentException("details view model class not found")
        }
    }
}