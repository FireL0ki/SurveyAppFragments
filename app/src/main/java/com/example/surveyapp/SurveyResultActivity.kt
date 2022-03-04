package com.example.surveyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


// create an extra to retrieve the data from mainActivity into SurveyResultsActivity
const val EXTRA_YES_COUNT = ".com.example.surveyapp.yesCount"
const val EXTRA_NO_COUNT = ".com.example.surveyapp.noCount"

// Screen that displays results (yes/no answers)
// two buttons: resetButton -- reset counts to zero
// continueSurveyButton -- returns user to the survey activity, keeps same totals


class SurveyResultActivity : AppCompatActivity() {

    private lateinit var yesCountText: TextView
    private lateinit var noCountText: TextView
    private lateinit var continueSurveyButton: Button
    private lateinit var resetButton: Button


    // create variables for extra values being retrieved
    private var yesCount = 0
    private var noCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        // retrieve values from extras and set to variables
        yesCount = intent.getIntExtra(EXTRA_YES_COUNT, 0)
        noCount = intent.getIntExtra(EXTRA_NO_COUNT, 0)

        // connect other components from SurveyResultActivity screen
        // these are the textViews that will hold the counts
        yesCountText = findViewById(R.id.yes_count)
        noCountText = findViewById(R.id.no_count)

        continueSurveyButton = findViewById(R.id.continue_survey_button)
        resetButton = findViewById(R.id.reset_button)

        // upon entering this page, counts should already be wired up to the textViews
        yesCountText.setText(yesCount)
        noCountText.setText(noCount)

        // set up event listeners for reset button (set counts to zero)
        resetButton.setOnClickListener {
            yesCountText.setText(0)
            noCountText.setText(0)
        }
        // continue to survey (to send user back to survey)
        continueSurveyButton.setOnClickListener {
            finish()
        }

    }

    // create companion object to access functions without a class instance
    companion object {
        // add additional arguments to newIntent to pass both yes & no counts
        fun newIntent(packageContext: Context, yesCount: Int, noCount: Int): Intent {
            return Intent(packageContext, SurveyResultActivity::class.java).apply {
                putExtra(EXTRA_YES_COUNT, yesCount)
                putExtra(EXTRA_NO_COUNT, noCount)
            }
        }
    }


}