package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
  private val diceImage: ImageView by lazy {
    findViewById(R.id.dice_image)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.button_roll)?.setOnClickListener {
      rollDice()
    }
  }

  private fun rollDice() {
    val drawableResource = when ((1..6).random()) {
      1 -> R.drawable.dice_1
      2 -> R.drawable.dice_2
      3 -> R.drawable.dice_3
      4 -> R.drawable.dice_4
      5 -> R.drawable.dice_5
      else -> R.drawable.dice_6
    }

    diceImage.setImageResource(drawableResource)
  }

}