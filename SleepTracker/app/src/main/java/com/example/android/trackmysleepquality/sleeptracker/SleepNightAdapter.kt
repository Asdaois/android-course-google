package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter :
  ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class ViewHolder private constructor(item: View) : RecyclerView.ViewHolder(item) {
    private val res = item.context.resources
    private val sleepLength: TextView = item.findViewById(R.id.sleep_length)
    private val sleepQuality: TextView = item.findViewById(R.id.quality_string)
    private val qualityImage: ImageView = item.findViewById(R.id.quality_image)

    fun bind(
      item: SleepNight
    ) {
      sleepLength.text =
        convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
      sleepQuality.text = convertNumericQualityToString(item.sleepQuality, res)
      qualityImage.setImageResource(
        when (item.sleepQuality) {
          0 -> R.drawable.ic_sleep_0
          1 -> R.drawable.ic_sleep_1
          2 -> R.drawable.ic_sleep_2
          3 -> R.drawable.ic_sleep_3
          4 -> R.drawable.ic_sleep_4
          5 -> R.drawable.ic_sleep_5
          else -> R.drawable.ic_sleep_active
        }
      )
    }

    companion object {
      fun from(parent: ViewGroup): ViewHolder {
        return ViewHolder(
          LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_sleep_night, parent, false
          )
        )
      }
    }
  }
}


class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
  override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
    return oldItem.nightId == newItem.nightId
  }

  override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
    return oldItem == newItem
  }
}