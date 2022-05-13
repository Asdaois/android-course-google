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

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

const val MAIN_TABLE = "daily_sleep_quality_table"

@Dao
interface SleepDatabaseDao {
  @Insert
  fun insert(night: SleepNight)

  @Update
  fun update(night: SleepNight)

  @Query("SELECT * FROM $MAIN_TABLE WHERE nightId = :key")
  fun get(key: Long): SleepNight?

  @Query("SELECT * FROM $MAIN_TABLE ORDER BY nightId DESC LIMIT 1")
  fun getTonight(): SleepNight?

  @Query("SELECT * FROM $MAIN_TABLE ORDER BY nightId DESC")
  fun getAllNights(): LiveData<List<SleepNight>>

  @Query("DELETE FROM $MAIN_TABLE")
  fun clear()

  @Query("DELETE FROM $MAIN_TABLE WHERE nightId = :key")
  fun clear(key: Long)

}