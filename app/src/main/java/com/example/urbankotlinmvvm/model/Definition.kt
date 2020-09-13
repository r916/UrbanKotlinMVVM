package com.example.urbankotlinmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "definitions"
)
data class Definition(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val current_vote: String,
    val defid: Int,
    val definition: String,
    val example: String,
    val permalink: String,
    //val sound_urls: List<String>,
    val thumbs_down: Int,
    val thumbs_up: Int,
    val word: String,
    val written_on: String
)