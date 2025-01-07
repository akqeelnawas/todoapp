package com.test.todoapp.usecase

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository

class FetchAllToDoItemsUseCase(
    private val repository: ToDoRepository,
) {
    suspend operator fun invoke(): List<ToDoItem> {
        return repository.getAllItems()
    }

}
