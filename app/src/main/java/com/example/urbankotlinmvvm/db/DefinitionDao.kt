package com.example.urbankotlinmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.urbankotlinmvvm.model.Definition

@Dao
interface DefinitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(definition: Definition): Long

    @Query("SELECT * FROM definitions")
    fun getAllDefinitions(): LiveData<List<Definition>>

    @Delete
    suspend fun deleteDefinition(definition: Definition)

}
