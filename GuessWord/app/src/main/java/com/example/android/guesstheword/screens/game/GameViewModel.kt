package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.*

class GameViewModel : ViewModel() {
  // The current word
  private val _word: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
  val word: LiveData<String>
    get() = _word

  // The current score
  private val _score: MutableLiveData<Int> by lazy {
    MutableLiveData(0)
  }
  val score: LiveData<Int>
    get() = _score

  private val gameFinished: MutableLiveData<Boolean> by lazy {
    MutableLiveData(false)
  }
  val isGameFinished: LiveData<Boolean>
    get() = gameFinished
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
      gameFinished.value = true
    } else {
      _word.value = wordList.removeAt(0)
    }
  }

  /** Methods for buttons presses **/

  fun onSkip() {
    _score.value = _score.value?.minus(1)
    nextWord()
  }

  fun onCorrect() {
    _score.value = _score.value?.plus(1)
    nextWord()
  }

  fun onGameFinishComplete() {
    gameFinished.value = false
  }
}