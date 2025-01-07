package com.appetiserapps.testapplication.usecase

import com.appetiserapps.testapplication.model.ToDoItem
import com.appetiserapps.testapplication.repository.ToDoRepository

class RemoveToDoItemUseCase {

    private val repository: ToDoRepository = ToDoRepository()

    operator fun invoke(item: ToDoItem): Boolean {
        return repository.removeItem(item)
    }

}
