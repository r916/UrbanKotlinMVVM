package com.example.urbankotlinmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.urbankotlinmvvm.repo.Repository

class DefinitionViewModel(
    val definitionRepository: Repository
) : ViewModel() {
}