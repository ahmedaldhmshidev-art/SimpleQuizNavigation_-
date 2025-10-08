package com.example.navigationcomponent_quiz.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "question")
data class EntityItemsQuestions(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val questionText : String,
    val option1 : String ,
    val option2 : String ,
    val option3 : String ,
    val correctAnswer : Int
)
