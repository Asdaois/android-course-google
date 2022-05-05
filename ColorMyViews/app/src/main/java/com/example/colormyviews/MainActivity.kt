package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setListeners()
  }

  private fun setListeners() {
    val clickableViewsIds = listOf(
      R.id.box_one_text,
      R.id.box_two_text,
      R.id.box_three_text,
      R.id.box_four_text,
      R.id.box_five_text,
      R.id.constraint_layout,
      R.id.button_red,
      R.id.button_yellow,
      R.id.button_green
    )

    clickableViewsIds.forEach { id ->
      findViewById<View>(id).setOnClickListener { view -> makeColored(view) }
    }
  }

  private fun makeColored(aView: View) {
    val color = when (aView.id) {
      R.id.box_one_text -> Color.BLACK
      R.id.box_two_text -> Color.BLUE
      R.id.box_three_text -> Color.GREEN
      R.id.box_four_text -> Color.RED
      R.id.box_five_text -> Color.YELLOW
      R.id.button_red -> Color.RED
      R.id.button_yellow -> Color.YELLOW
      R.id.button_green -> Color.GREEN

      else -> Color.LTGRAY
    }

    aView.setBackgroundColor(color)
  }
}