package com.example.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<Button>(R.id.button_done)?.setOnClickListener {
      hideKeyboard()
      addNickname()
      it.visibility = Button.GONE
    }

  }

  private fun hideKeyboard() {
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
  }

  private fun addNickname() {
    showNickname(getNickname())
  }

  private fun showNickname(aNickname: String) {
    findViewById<TextView>(R.id.nickname_text).let {
      it.text = "Hi!, my friends call me $aNickname"
      it.visibility = TextView.VISIBLE
    }
  }

  private fun getNickname(): String {
    var result: String = ""
    findViewById<EditText>(R.id.nickname_edit)?.let {
      result = it.text.toString()
      it.visibility = EditText.GONE
    }
    return result
  }
}