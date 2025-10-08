package com.example.navigationcomponent_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationcomponent_quiz.databinding.FragmentInfoFragQuizBinding

class InfoFragQuiz : Fragment() {
    private lateinit var binding: FragmentInfoFragQuizBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentInfoFragQuizBinding . inflate(inflater , container ,false)
        return binding.root
    }


}