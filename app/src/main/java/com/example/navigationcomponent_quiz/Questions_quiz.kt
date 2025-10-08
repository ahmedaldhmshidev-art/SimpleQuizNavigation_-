package com.example.navigationcomponent_quiz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent_quiz.Questions_quizDirections
import com.example.navigationcomponent_quiz.R
import com.example.navigationcomponent_quiz.RoomDatabase.EntityItemsQuestions
import com.example.navigationcomponent_quiz.RoomDatabase.MyApp
import com.example.navigationcomponent_quiz.RoomDatabase.QuizUiState
import com.example.navigationcomponent_quiz.RoomDatabase.ViewModelQuestion
import com.example.navigationcomponent_quiz.databinding.FragmentQuestionsQuizBinding
class Questions_quiz : Fragment() {

    private val viewMode: ViewModelQuestion by viewModels {
        ViewModelQuestion.ModelFactory((requireActivity().application as MyApp).repository)
    }
    private lateinit var binding: FragmentQuestionsQuizBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentQuestionsQuizBinding.inflate(inflater, container, false)
//من هنا تم أستدعاء الحالات من الكلاس المغلق وفحص الحالة الحالية
        viewMode.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
//اذا كنا في حالة العرض ارسل البيانات الي دالة عرض البيانات في xml
                is QuizUiState.ShowQuestion -> showQuestionToXml(state.questions)
// الحالة هذه اذا انتهت الاسالة اذا تم عرض جميع الاسالة يرسل النتيجة وينقلها الي فراق مناسب
                is QuizUiState.Finished ->
                    if (state.isWin) {
                        val action = Questions_quizDirections
                            .actionQuestionsQuizToWantFragQuiz(state.correct, state.wrong)
                        findNavController().navigate(action)
                    } else {
                        val action = Questions_quizDirections
                            .actionQuestionsQuizToGameFragQuiz(state.correct, state.wrong)
                        findNavController().navigate(action)
                    }
            }
        }

        binding.btnSubmit.setOnClickListener {
            val selectedId = binding.rgQuestionRadioGroup.checkedRadioButtonId

            val answerIndex = when (selectedId) {
                R.id.btn_answerRadio1 -> 0
                R.id.btn_answerRadio2 -> 1
                R.id.btn_answerRadio3 -> 2
                else -> -1
            }

            if (answerIndex != -1) {
                viewMode.submitAnswer(answerIndex)
                binding.rgQuestionRadioGroup.clearCheck()
            }
        }

        return binding.root
    }

    private fun showQuestionToXml(questions: EntityItemsQuestions) {
        binding.tvQuestionText .text = questions.questionText
        binding.btnAnswerRadio1.text = questions.option1
        binding.btnAnswerRadio2.text = questions.option2
        binding.btnAnswerRadio3.text = questions.option3
    }
}
