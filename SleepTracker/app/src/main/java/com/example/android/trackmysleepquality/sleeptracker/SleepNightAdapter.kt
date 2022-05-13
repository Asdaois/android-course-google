package com.example.android.trackmysleepquality.sleeptracker

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
  val data = listOf<SleepNight>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
    TODO("Not yet implemented")
  }

  override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
    holder.textView.text = data[position].sleepQuality.toString()
  }

  override fun getItemCount() = data.size
}

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
