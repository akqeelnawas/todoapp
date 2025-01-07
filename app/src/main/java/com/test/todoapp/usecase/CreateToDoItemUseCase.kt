package com.test.todoapp.usecase

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository

class CreateToDoItemUseCase {

    private val repository: ToDoRepository = ToDoRepository()

    operator fun invoke(name: String): ToDoItem? {
        if (name.isEmpty()) return null
        val count = repository.getCount()
        val item = ToDoItem(id = count + 1, name = name)
        repository.createItem(item)
        return item
    }

}
