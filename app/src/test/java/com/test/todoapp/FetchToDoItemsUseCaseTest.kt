package com.test.todoapp

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.usecase.FetchAllToDoItemsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FetchToDoItemsUseCaseTest {

    @Test
    fun `test invoke`() = runBlocking {
        val repository = mockk<ToDoRepository>()
        val testItems = arrayListOf(
            ToDoItem(id = 1, name = "Test 1"),
            ToDoItem(id = 2, name = "Test 2"),
            ToDoItem(id = 3, name = "Test 3"),
            ToDoItem(id = 4, name = "Test 4")
        )

        coEvery { repository.getAllItems() } returns testItems

        val useCase = FetchAllToDoItemsUseCase(repository)
        val result = useCase.invoke()

        Assert.assertEquals(testItems, result)
    }
}
