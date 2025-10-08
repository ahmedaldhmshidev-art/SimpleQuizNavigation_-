package com.example.navigationcomponent_quiz.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(vararg questions: EntityItemsQuestions)

    @Query ("SELECT * FROM question")
    suspend fun getAllQuestion() : List<EntityItemsQuestions>
}