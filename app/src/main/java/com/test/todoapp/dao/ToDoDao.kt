package com.test.todoapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.test.todoapp.model.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<ToDoEntity>

    @Insert
    suspend fun saveToDoItem(entity: ToDoEntity): Long

    @Delete
    suspend fun deleteToDoItem(entity: ToDoEntity): Int

}
