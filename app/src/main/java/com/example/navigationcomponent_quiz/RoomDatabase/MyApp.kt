package com.example.navigationcomponent_quiz.RoomDatabase

import android.app.Application
// هذا الكلاس يجعل قاعدة البيانات و ريبوزتري معروضه لكل التطبيق
class MyApp : Application() {
    val db by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { QuestionRepository(db.questionDao()) }
}