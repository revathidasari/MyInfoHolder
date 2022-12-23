package com.example.mymodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TrackMonthlyViewModelFactory(
    private val repository: TrackMonthlyRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrackMonthlyViewModel(repository) as T
    }

}