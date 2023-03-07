package com.group.anmpweek2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_result.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtNameGame.setText("$playerName's Turn")
        }
        var number1 = (0..50).shuffled().last();
        var number2 = (0..50).shuffled().last();
        var hasil = number1+number2;
        var score = 0;
        txtQuestion.setText("$number1 + $number2")


        btnSubmit.setOnClickListener {
            var jawaban = txtAnswer.text.toString()
            if(jawaban == hasil.toString()){
                score += 1
                number1 = (0..50).shuffled().last();
                number2 = (0..50).shuffled().last();
                hasil = number1+number2;
                txtQuestion.setText("$number1 + $number2")
                txtAnswer.setText("")
            }
            else{
                val action = GameFragmentDirections.actionToResultFragment(score.toString())
                Navigation.findNavController(it).navigate(action)
            }

        }
    }

}