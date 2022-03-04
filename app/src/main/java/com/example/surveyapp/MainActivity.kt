package com.example.surveyapp

import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)
        yesCountText = findViewById(R.id.yes_count)
        noCountText = findViewById(R.id.no_count)

        // remove reset button and set up resultsButton
        resultsButton = findViewById(R.id.results_button)


        // onCreate, set initial vote counts to 0
        val initialCount = 0
        noCountText.text = getString(R.string.no_count_text, initialCount)
        yesCountText.text = getString(R.string.yes_count_text, initialCount)

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

        // remove resetButton and set up resultsButton
//        resetButton.setOnClickListener {
//            surveyAppViewModel.resetCounts()
//            yesCountText.text = getString(R.string.no_count_text, surveyAppViewModel.noCount)
//            noCountText.text = getString(R.string.yes_count_text, surveyAppViewModel.yesCount)
//        }

        resultsButton.setOnClickListener {
            // when this button is tapped, display SurveyResultActivity--
            // to display the results of the survey (how many yes/no answers)
            val yesCount = surveyAppViewModel.yesCount
            val noCount = surveyAppViewModel.noCount

            val intent = SurveyResultActivity.newIntent(this@MainActivity, yesCount, noCount)
            startActivity(intent)
//            showResultsPage()
        }
    }

    // set up function to send data to new SurveyResultActivity and run the new page
//    private fun showResultsPage() {
//        val showResultsIntent = Intent(this, SurveyResultActivity::class.java)
//        startActivity(intent)
//
//        showResultsIntent.putExtra(EXTRA_VOTE_RESULTS, surveyAppViewModel.noCount)
//        showResultsIntent.putExtra(EXTRA_VOTE_RESULTS, surveyAppViewModel.yesCount)
//        voteResultLauncher.launch(showResultsIntent)
//
//
//    }

}