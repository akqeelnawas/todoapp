package com.test.todoapp.di

import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.usecase.CreateToDoItemUseCase
import com.test.todoapp.usecase.FetchAllToDoItemsUseCase
import com.test.todoapp.usecase.RemoveToDoItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ToDoUseCaseModule {

    @Provides
    fun provideCreateToDoItemUseCase(
        repository: ToDoRepository
    ): CreateToDoItemUseCase {
        return CreateToDoItemUseCase(repository)
    }

    @Provides
    fun provideFetchAllToDoItemsUseCase(
        repository: ToDoRepository
    ): FetchAllToDoItemsUseCase {
        return FetchAllToDoItemsUseCase(repository)
    }

    @Provides
    fun provideRemoveToDoItemUseCase(
        repository: ToDoRepository
    ): RemoveToDoItemUseCase {
        return RemoveToDoItemUseCase(repository)
    }

}
