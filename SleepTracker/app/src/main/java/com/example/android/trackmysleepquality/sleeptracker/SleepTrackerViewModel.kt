/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
  val database: SleepDatabaseDao,
  application: Application
) : AndroidViewModel(application) {

  private val tonight = MutableLiveData<SleepNight?>()
  val nights = database.getAllNights()
  val nightsFormatted = Transformations.map(nights) { nights ->
    formatNights(nights, application.resources)
  }
  val isStartButtonEnabled = Transformations.map(tonight) {
    null == it
  }

  val isStopButtonEnabled = Transformations.map(tonight) {
    null != it
  }

  val isClearButtonEnabled = Transformations.map(nights) {
    it?.isNotEmpty()
  }

  private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
  val navigateToSleepQuality: LiveData<SleepNight>
    get() = _navigateToSleepQuality

  fun doneNavigating() {
    _navigateToSleepQuality.value = null
  }

  init {
    initializeTonight()
  }

  private fun initializeTonight() {
    viewModelScope.launch {
      tonight.value = getTonightFromDatabase()
    }
  }

  private suspend fun getTonightFromDatabase(): SleepNight? {
    return withContext(Dispatchers.IO) {
      var night = database.getTonight()
      if (night?.startTimeMilli != night?.endTimeMilli) {
        night = null
      }
      night
    }
  }

  fun onStartTracking() {
    viewModelScope.launch {
      val newNight = SleepNight()
      insert(newNight)
      tonight.value = getTonightFromDatabase()
    }
  }

  private suspend fun insert(night: SleepNight) {
    withContext(Dispatchers.IO) {
      database.insert(night)
    }
  }

  fun onStopTracking() {
    viewModelScope.launch {
      val oldNight = tonight.value ?: return@launch
      oldNight.endTimeMilli = System.currentTimeMillis()
      update(oldNight)
      tonight.value = null
      _navigateToSleepQuality.value = oldNight
    }
  }

  private suspend fun update(night: SleepNight) {
    withContext(Dispatchers.IO) {
      database.update(night)
    }
  }

  fun onClear() {
    viewModelScope.launch {
      clear()
      tonight.value = null
    }
  }

  private suspend fun clear() {
    withContext(Dispatchers.IO) {
      database.clear()
    }
  }

  private val _navigateToSleepDataQuality = MutableLiveData<Long>()
  val navigateToSleepDataQuality: LiveData<Long>
    get() = _navigateToSleepDataQuality

  fun onSleepNightClicked(nightId: Long) {
    _navigateToSleepDataQuality.value = nightId
  }

  fun onSleepDataQualityNavigated() {
    _navigateToSleepDataQuality.value = null
  }
}

