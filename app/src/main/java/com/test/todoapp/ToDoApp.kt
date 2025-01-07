package com.test.todoapp

import android.app.Application
import androidx.room.Room
import com.test.todoapp.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApp: Application() {

    val appDatabase by lazy {

    }

}
