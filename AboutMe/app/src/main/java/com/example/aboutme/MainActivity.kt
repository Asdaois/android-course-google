package com.example.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    binding.buttonDone.setOnClickListener {
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
    binding.nicknameText.apply {
      text = getString(R.string.greet_with_nickname, aNickname)
      visibility = TextView.VISIBLE
    }
  }

  private fun getNickname(): String {
    var result: String

    binding.nicknameEdit.apply {
      result = text.toString()
      visibility = EditText.GONE
    }
    binding.invalidateAll()

    return result
  }
}