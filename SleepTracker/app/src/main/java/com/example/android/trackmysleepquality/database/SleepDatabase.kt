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

import androidx.room.Database

const val SLEEP_HISTORY_DATABASE = "sleep_history_database"

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : androidx.room.RoomDatabase() {
  abstract val sleepDatabaseDao: SleepDatabaseDao

  companion object {
    @Volatile
    private var INSTANCE: SleepDatabase? = null

    fun getInstance(context: android.content.Context): SleepDatabase {
      synchronized(this) {
        var instance = INSTANCE
        if (instance == null) {
          instance = androidx.room.Room.databaseBuilder(
            context.applicationContext,
            SleepDatabase::class.java,
            SLEEP_HISTORY_DATABASE
          ).fallbackToDestructiveMigration().build()
          INSTANCE = instance
        }
        return instance
      }
    }
  }
}