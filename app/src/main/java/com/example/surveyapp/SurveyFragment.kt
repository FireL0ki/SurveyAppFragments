package com.example.surveyapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View

class SurveyFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesCountText: TextView
    private lateinit var noCountText: TextView

    // remove resetButton and replace with resultButton
    private lateinit var resultsButton: Button


    private val surveyAppViewModel: SurveyAppViewModel by lazy {
        ViewModelProvider(this).get(SurveyAppViewModel::class.java)
    }

    override fun OnCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstancesState: Bundle?
    ): View? {
        // inflate layout for this fragment
        val view = inflater.inflate(R.layout.activity_survey_fragment, container, false)
        // can't use findViewById, have to specify which view to look in
        questionTextView = view.findViewById(R.id.question_text_view)
        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)
        yesCountText = view.findViewById(R.id.yes_count)
        noCountText = view.findViewById(R.id.no_count)

        resultsButton = view.findViewById(R.id.results_button)

        // TODO need this ?
        val initialCount = 0
        noCountText.text = getString(R.string.no_count_text, initialCount)
        yesCountText.text = getString(R.string.yes_count_text, initialCount)


        // add event listeners here
        yesButton.setOnClickListener {
            surveyAppViewModel.updateYesCount()
            val yesCount = surveyAppViewModel.yesCount
            yesCountText.text = getString(R.string.yes_count_text, yesCount)
        }

        noButton.setOnClickListener {
            surveyAppViewModel.updateNoCount()
            val noCount = surveyAppViewModel.noCount
            noCountText.text = getString(R.string.no_count_text, noCount)
        }

        return view
    }

    // TODO add functions here


    // TODO need this?
    companion object {
        @JvmStatic
        fun newInstance() = SurveyFragment()
    }

}
