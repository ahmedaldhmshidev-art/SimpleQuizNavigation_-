package com.example.navigationcomponent_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.serialization.generateRouteWithArgs
import com.example.navigationcomponent_quiz.databinding.FragmentGameFragQuizBinding

class GameFragQuiz : Fragment() {


    private lateinit var binding: FragmentGameFragQuizBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentGameFragQuizBinding.inflate(inflater , container , false)


        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragQuiz_to_questions_quiz)
        }
// استقبال البيانات من fragment اخرا
        val args = GameFragQuizArgs.fromBundle(requireArguments())
        binding.tvTotalScore   .text = args.numCorrect .toString()
        binding.tvWrongAnswers .text = args.numWrong   .toString()

        return binding.root
    }


}