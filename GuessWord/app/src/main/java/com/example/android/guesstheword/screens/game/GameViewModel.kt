package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
  init {
    Log.i("GVM","GameViewModel created!")
  }

  override fun onCleared() {
    super.onCleared()
    Log.i("GVM","GameViewModel destroyed!")
  }
}