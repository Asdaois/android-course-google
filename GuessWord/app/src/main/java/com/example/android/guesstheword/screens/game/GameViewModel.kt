package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
  // The current word
  var word = ""
    private set(value) {
      field = value
      Log.i("GameViewModel", "word is now $word")
    }

  // The current score
  var score = 0
    private set(value) {
      field = value
      Log.i("GameViewModel", "score is now $score")
    }

  // The list of words - the front of the list is the next word to guess
  private lateinit var wordList: MutableList<String>

  init {
    Log.i("GVM", "GameViewModel created!")
    resetList()
    nextWord()
  }

  override fun onCleared() {
    super.onCleared()
    Log.i("GVM", "GameViewModel destroyed!")
  }

  /**
   * Resets the list of words and randomizes the order
   */
  private fun resetList() {
    wordList = mutableListOf(
      "queen",
      "hospital",
      "basketball",
      "cat",
      "change",
      "snail",
      "soup",
      "calendar",
      "sad",
      "desk",
      "guitar",
      "home",
      "railway",
      "zebra",
      "jelly",
      "car",
      "crow",
      "trade",
      "bag",
      "roll",
      "bubble"
    )
    wordList.shuffle()
  }

  /**
   * Moves to the next word in the list
   */
  private fun nextWord() {
    //Select and remove a word from the list
    if (wordList.isEmpty()) {
//      gameFinished()
    } else {
      word = wordList.removeAt(0)
    }
  }

  /** Methods for buttons presses **/

  fun onSkip() {
    score--
    nextWord()
  }

  fun onCorrect() {
    score++
    nextWord()
  }
}