package com.test.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.todoapp.dao.ToDoDao
import com.test.todoapp.model.ToDoEntity

@Database(
    entities = [
        ToDoEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}
