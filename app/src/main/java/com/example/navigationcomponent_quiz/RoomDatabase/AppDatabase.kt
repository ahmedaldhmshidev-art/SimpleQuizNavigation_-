package com.example.navigationcomponent_quiz.RoomDatabase

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityItemsQuestions::class] , version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao():QuestionDao


    companion object
    {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase ::class.java,
                    "task_item_database"

                ) . fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance

            }
        }
    }
}