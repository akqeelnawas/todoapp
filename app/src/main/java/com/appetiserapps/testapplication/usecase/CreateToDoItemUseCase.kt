package com.appetiserapps.testapplication.usecase

import com.appetiserapps.testapplication.model.ToDoItem
import com.appetiserapps.testapplication.repository.ToDoRepository

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
