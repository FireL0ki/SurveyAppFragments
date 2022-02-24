package com.example.surveyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    /* you are surveying MCTC students with a simple question with a Yes/No answer.
Create a main Activity that shows your survey question in a TextView.
Add two buttons, one for Yes and one for No
Add two TextView components, one to show the total Yes votes, and one to show the total No votes.
As you survey students, every time you ask someone the question and they answer 'yes', you can tap the Yes button to add 1 to the Yes total.  And if someone answers 'no', you can tap the No button, and this should add 1 to the no total. The yes and no TextViews should update as you collect votes.
Add a reset button to reset the totals to zero.
Make sure the total votes for Yes and No are saved when the device is rotated. Use a ViewModel.
An example layout is shown below with 16 yes votes and 17 no votes.  If the reset button was tapped, both counts should reset to 0.  Then, if the yes button was tapped, the yes count is set to 1.
*/

    private lateinit var questionTextView: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesCountText: TextView
    private lateinit var noCountText: TextView
    private lateinit var resetButton: Button


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
        resetButton = findViewById(R.id.reset_button)


//        surveyAppViewModel. // use this to call functions in viewModel

        // fix errors -- yesCount/yesCountText variables are moved over to view model
        // GuestList app has examples for set up

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

        resetButton.setOnClickListener {
            surveyAppViewModel.resetCounts()
            yesCountText.text = getString(R.string.no_count_text, surveyAppViewModel.noCount)
            noCountText.text = getString(R.string.yes_count_text, surveyAppViewModel.yesCount)
        }

    }
}