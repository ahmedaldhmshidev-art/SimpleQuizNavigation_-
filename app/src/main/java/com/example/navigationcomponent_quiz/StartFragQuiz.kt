package com.example.navigationcomponent_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.navigationcomponent_quiz.databinding.FragmentStartFragQuizBinding

class StartFragQuiz : Fragment() {

    private lateinit var binding : FragmentStartFragQuizBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartFragQuizBinding.inflate(inflater, container , false)



        binding.btnStartQuesId.setOnClickListener {
            view : View ->
            view . findNavController().navigate(R.id.action_startFragQuiz_to_questions_quiz)
        }


//        تصريح لنظام عن وجود option menu
        setHasOptionsMenu(true)
        return binding.root
    }
//    دالة لانشاء او انفلات لل menu option
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_option, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
//    هذه الدالة مثل onClickLasentenr عند الضغط علا عنصر تشتغل
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI .onNavDestinationSelected(item , findNavController()) ||
                super.onOptionsItemSelected(item) 
    }
}