package com.example.navigationcomponent_quiz.RoomDatabase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ViewModelQuestion(private val repository: QuestionRepository) : ViewModel() {


// كأن من الكلاس المغلق يحمل الحالات مع انه مراقب قابل لتعديل
    private val _uiStat = MutableLiveData<QuizUiState>()
    val uiState: LiveData<QuizUiState> = _uiStat

    // ققايمة من ملحملة نفس خصائص جدول البيانات نجلب او نخزن فيها البيانات من قاعدة البيانات
    private var storyQuestion: List<EntityItemsQuestions> = emptyList()

    private var currentIndex  = 0
    private var correctAnswer = 0
    private var wrongAnswer   = 0

    init {
        viewModelScope.launch {
            repository.insertQuestions(
                EntityItemsQuestions(
                    questionText = "ماهي عاصمة اليمن ؟",
                    option1 = "صنعاء",
                    option2 = "عدن",
                    option3 = "تعز",
                    correctAnswer = 0
                ), EntityItemsQuestions(
                    questionText = "ماهي عاصمة السعودية ؟",
                    option1 = "جدة",
                    option2 = "الدمام",
                    option3 = "الرياض",
                    correctAnswer = 2
                ), EntityItemsQuestions(
                    questionText = "ماهي عاصمة سوريا ؟",
                    option1 = "ديمشق",
                    option2 = "حلب",
                    option3 = "الرقة",
                    correctAnswer = 0
                )
            )
//تحميل البيانات الي قايمة
            storyQuestion = repository.gerAllQuestions()
//     فحص القايمة انها غير فاضي
            if (storyQuestion.isNotEmpty()) {
                _uiStat.postValue(QuizUiState.ShowQuestion(storyQuestion[currentIndex]))
            }
        }
    }
//
//    fun submitAnswer(answerIndex: Int) {
//        if (storyQuestion.isEmpty() || currentIndex >= storyQuestion.size) return
//
//        val currentQuestion = storyQuestion[currentIndex]
//
//        if (answerIndex == currentQuestion.correctAnswer) {
//            correctAnswer++
//            currentIndex++
//        } else {
//            wrongAnswer++
//            currentIndex++
//        }
//
//        // الشرط الأول: لو الأخطاء وصلت 3 → خسارة
//        if (correctAnswer == 3 ) {
//            _uiStat.postValue(QuizUiState.Finished(correctAnswer, wrongAnswer, true))
//            return
//        }
//
//        // الشرط الثاني: لو خلصت الأسئلة → فوز (حتى لو في أخطاء أقل من 3)
//        if (currentIndex >= storyQuestion.size) {
//            _uiStat.postValue(QuizUiState.Finished(correctAnswer, wrongAnswer, false))
//            return
//        }
//
//        // باقي أسئلة → عرض السؤال التالي
//        _uiStat.postValue(QuizUiState.ShowQuestion(storyQuestion[currentIndex]))
//    }
fun submitAnswer(answerIndex: Int) {
    if (storyQuestion.isEmpty() || currentIndex >= storyQuestion.size) return

    val currentQuestion = storyQuestion[currentIndex]

    // التحقق من الإجابة وتحديث العداد
    if (answerIndex == currentQuestion.correctAnswer) {
        correctAnswer++
        currentIndex++
    } else {
        wrongAnswer++
        currentIndex++
    }



//    if (correctAnswer > 0 && wrongAnswer < 3){
//        _uiStat.postValue(QuizUiState.Finished(correctAnswer , wrongAnswer , true))
//    }

    // الشرط الأول: إذا وصلت الإجابات الصحيحة إلى 3 → فوز (true)
    if (correctAnswer == 3) {
        _uiStat.postValue(QuizUiState.Finished(correctAnswer, wrongAnswer, true))
        return
    }

    // الشرط الثاني: إذا وصلت الأخطاء إلى 3 → خسارة (false)
    if (wrongAnswer == 3) {
        _uiStat.postValue(QuizUiState.Finished(correctAnswer, wrongAnswer, false))
        return
    }

    // الشرط الثالث: إذا انتهت الأسئلة → التحقق من النتيجة
    if (currentIndex >= storyQuestion.size) {
        // إذا كان هناك إجابة صحيحة على الأقل → فوز
        val isWinner = correctAnswer > 0
        _uiStat.postValue(QuizUiState.Finished(correctAnswer, wrongAnswer, isWinner))
        return
    }

    // إذا لم تتحقق أي من الشروط السابقة → عرض السؤال التالي
    _uiStat.postValue(QuizUiState.ShowQuestion(storyQuestion[currentIndex]))
}

    class ModelFactory(private val repository: QuestionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelQuestion::class.java))

                @Suppress("UNCHECKED_CAST")
                return ViewModelQuestion(repository) as T

            throw IllegalArgumentException("Unknown Class for View Model")
        }
    }
}

