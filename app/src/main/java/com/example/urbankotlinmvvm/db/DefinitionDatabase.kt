package com.example.urbankotlinmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.urbankotlinmvvm.model.Definition

@Database(
    entities = [Definition::class],
    version = 1
)
abstract class DefinitionDatabase : RoomDatabase() {

    abstract fun getDefinitionDao(): DefinitionDao

    companion object {
        @Volatile
        private var instance: DefinitionDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DefinitionDatabase::class.java,
                "definition_db.db"
            ).build()
    }
}