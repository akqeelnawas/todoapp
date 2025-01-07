package com.appetiserapps.testapplication.usecase

import com.appetiserapps.testapplication.model.ToDoItem
import com.appetiserapps.testapplication.repository.ToDoRepository

class FetchAllToDoItemsUseCase {
    private val repository: ToDoRepository = ToDoRepository()

    operator fun invoke(): List<ToDoItem> {
        return repository.getAllItems()
    }

}
