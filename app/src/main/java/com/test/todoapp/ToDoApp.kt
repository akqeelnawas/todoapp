package com.test.todoapp

import android.app.Application
import androidx.room.Room
import com.test.todoapp.database.AppDatabase

class ToDoApp: Application() {

    val appDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }

}
