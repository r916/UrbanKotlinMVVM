package com.example.urbankotlinmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbankotlinmvvm.model.UrbanResponse
import com.example.urbankotlinmvvm.repo.Repository
import com.example.urbankotlinmvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class DefinitionViewModel(
    val definitionRepository: Repository
) : ViewModel() {

    val definitions: MutableLiveData<Resource<UrbanResponse>> = MutableLiveData()

    init {
        getDefinitions("wut")
    }

    fun getDefinitions(term: String) = viewModelScope.launch {
        definitions.postValue(Resource.Loading())
        val response = definitionRepository.getDefinitions(term)
        definitions.postValue(handleDefinitionResponse(response))
    }

    private fun handleDefinitionResponse(response: Response<UrbanResponse>) : Resource<UrbanResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}