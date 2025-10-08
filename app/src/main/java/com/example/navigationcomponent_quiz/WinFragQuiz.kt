package com.example.navigationcomponent_quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent_quiz.databinding.FragmentWinFragQuizBinding

class WinFragQuiz : Fragment() {
    private lateinit var binding: FragmentWinFragQuizBinding
    private lateinit var args: WinFragQuizArgs



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentWinFragQuizBinding.inflate(inflater,container,false)

        binding.btnTryAgainWont.setOnClickListener {
            findNavController().navigate(R.id.action_wantFragQuiz_to_questions_quiz)
        }
// because show button sher
        setHasOptionsMenu(true)

//استقبال البيانات من fragment اخرا
        args = WinFragQuizArgs . fromBundle(requireArguments())
        binding . tvTotalScoreWont   . text = args . numCorrect . toString()
        binding . tvWrongAnswersWont . text = args . numWrong   .toString()


        return binding.root


    }
    private fun getIntent():Intent {
        args= WinFragQuizArgs.fromBundle(requireArguments())

//        create new intent
        val sharIntent = Intent(Intent.ACTION_SEND)
        sharIntent.setType("text/plain")
        sharIntent.putExtra(Intent.EXTRA_TEXT , getString(R.string.shar_text , args.numWrong , args.numCorrect))

        return sharIntent
    }
    private fun sendIntent(){
        startActivity(getIntent())
    }

// عمل انفلات للمينيو sher
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shar_wint, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }
// ماذا يحدث عند انقر علئ item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.item_shar_id -> sendIntent()
        }
        return super.onOptionsItemSelected(item)
    }

}