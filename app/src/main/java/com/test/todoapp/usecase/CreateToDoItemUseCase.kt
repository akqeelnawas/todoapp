package com.test.todoapp.usecase

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository

class CreateToDoItemUseCase(
    private val repository: ToDoRepository,
) {

    suspend operator fun invoke(name: String): ToDoItem? {
        if (name.isEmpty()) return null
        val item = ToDoItem(id = 0, name = name)
        val result = repository.createItem(item)
        if (result > 0) {
            return ToDoItem(result, item.name)
        }
        return null
    }

}
