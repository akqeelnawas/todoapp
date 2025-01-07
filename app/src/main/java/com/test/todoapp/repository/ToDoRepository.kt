package com.test.todoapp.repository

import com.test.todoapp.database.AppDatabase
import com.test.todoapp.model.ToDoEntity
import com.test.todoapp.model.ToDoItem
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    suspend fun createItem(item: ToDoItem): Int {
        val result = appDatabase.toDoDao().saveToDoItem(item.toToDoEntity())
        return result.toInt()
    }

    suspend fun removeItem(item: ToDoItem): Boolean {
        val result = appDatabase.toDoDao().deleteToDoItem(item.toToDoEntity())
        return result == 1
    }

    suspend fun getAllItems(): List<ToDoItem> = appDatabase.toDoDao().getAll()
        .map { it.toToDoItem() }

    private fun ToDoItem.toToDoEntity() = ToDoEntity(
        id = id,
        name = name
    )

    private fun ToDoEntity.toToDoItem() = ToDoItem(
        id = id,
        name = name
    )
}
