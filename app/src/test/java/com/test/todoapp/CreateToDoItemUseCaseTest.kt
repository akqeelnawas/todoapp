package com.test.todoapp

import com.test.todoapp.database.AppDatabase
import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.usecase.CreateToDoItemUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CreateToDoItemUseCaseTest {

    @Test
    fun `test invoke`() = runBlocking {
        val repository = mockk<ToDoRepository>()
        val testId = 2
        val testName = "Test"
        val item = ToDoItem(id = 0, name = testName)

        coEvery { repository.createItem(item) } returns testId

        val useCase = CreateToDoItemUseCase(repository)
        val result = useCase.invoke(item.name)

        Assert.assertNotNull(result)
        Assert.assertEquals(testId, result!!.id)
        Assert.assertEquals(testName, result.name)
    }

}