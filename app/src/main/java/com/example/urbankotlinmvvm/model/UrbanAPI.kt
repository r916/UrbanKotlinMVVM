package com.example.urbankotlinmvvm.model

import com.example.urbankotlinmvvm.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UrbanAPI {

    @GET("/define")
    suspend fun getDefinitions(
        @Query("term")
        term: String = "wat",
        @Header("X-RapidAPI-Key")
        apiKey: String = API_KEY
    ): Response<UrbanResponse>

}
