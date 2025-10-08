package com.example.navigationcomponent_quiz.RoomDatabase

class QuestionRepository(private val dao: QuestionDao) {
//    vararg تسمح بادخال متعدد
    suspend fun insertQuestions (vararg questions: EntityItemsQuestions)
//    * تعني تستلم بيانات علي شكل مصفوفة فصلها الي عناصر
    = dao.insertQuestions(*questions)
//
    suspend fun gerAllQuestions():List<EntityItemsQuestions>
    = dao.getAllQuestion()
}