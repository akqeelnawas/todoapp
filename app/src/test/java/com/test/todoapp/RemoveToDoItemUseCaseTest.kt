package com.test.todoapp

import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.usecase.CreateToDoItemUseCase
import com.test.todoapp.usecase.RemoveToDoItemUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RemoveToDoItemUseCaseTest {

    @Test
    fun `test invoke`() = runBlocking {
        val repository = mockk<ToDoRepository>()
        val testId = 2
        val testName = "Test"
        val item = ToDoItem(id = testId, name = testName)

        coEvery { repository.removeItem(item) } returns true

        val useCase = RemoveToDoItemUseCase(repository)
        val result = useCase.invoke(item)

        Assert.assertEquals(true, result)
    }

}