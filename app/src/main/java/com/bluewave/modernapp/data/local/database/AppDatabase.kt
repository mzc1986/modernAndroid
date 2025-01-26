package com.bluewave.modernapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OneModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun oneModelDao(): OneModelDao
}