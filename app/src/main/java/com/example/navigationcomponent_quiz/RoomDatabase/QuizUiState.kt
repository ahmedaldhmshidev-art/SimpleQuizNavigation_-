package com.example.navigationcomponent_quiz.RoomDatabase
//هذا النوع من الكلاس يعتبر مغلق او مخفي لا يستطيع اي كلاس الوراثه منة الا الذي بداخله
sealed class QuizUiState {
//    حالة العرض
    data class ShowQuestion(val questions: EntityItemsQuestions):QuizUiState()
//    حالة الانتها الاساله استقبال هذا الكلاس وارسال البيانات الازمة
    data class Finished(val correct:Int , val wrong:Int , val isWin :Boolean): QuizUiState()
}