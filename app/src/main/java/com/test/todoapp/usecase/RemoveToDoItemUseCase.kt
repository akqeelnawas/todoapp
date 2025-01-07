package com.test.todoapp.usecase

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository

class RemoveToDoItemUseCase {

    private val repository: ToDoRepository = ToDoRepository()

    operator fun invoke(item: ToDoItem): Boolean {
        return repository.removeItem(item)
    }

}
