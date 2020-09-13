package com.example.urbankotlinmvvm.repo

import com.example.urbankotlinmvvm.db.DefinitionDatabase
import com.example.urbankotlinmvvm.model.RetrofitInstance

class Repository(
    val db: DefinitionDatabase
) {
    suspend fun getDefinitions(term: String) =
        RetrofitInstance.api.getDefinitions(term)

}