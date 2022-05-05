package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

  val finalScore: LiveData<Int> = MutableLiveData(finalScore)

  private val _eventPlayAgain: MutableLiveData<Boolean> = MutableLiveData()
  val eventPlayAgain: LiveData<Boolean>
    get() = _eventPlayAgain

  init {
    Log.i("ScoreViewModel", "Final score is $finalScore")
  }

  fun onPlayAgain() {
    _eventPlayAgain.value = true
  }

}