package com.example.homework_20_rooms.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_20_rooms.data.local.dao.UserDao
import com.example.homework_20_rooms.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
