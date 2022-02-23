package com.example.surveyapp

import androidx.lifecycle.ViewModel

class SurveyAppViewModel : ViewModel() {
    // set up view model to hold yes/no count data on rotation

    // set up counters for yes & no answer options
    var yesCount = 0
    var noCount = 0

    // set up functions here in the viewModel that will be called from the main program


    fun updateYesCount() {
        yesCount++
        yesCountText.text = getString(R.string.yes_count_text, yesCount)
    }

    fun updateNoCount() {
        noCount++
        yesCountText.text = getString(R.string.no_count_text, noCount)
    }

}