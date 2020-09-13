package com.example.urbankotlinmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbankotlinmvvm.repo.Repository

class DefinitionViewModelProviderFactory(
    val Repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  DefinitionViewModel(Repository) as T
    }
}