package com.test.todoapp.di

import com.test.todoapp.database.AppDatabase
import com.test.todoapp.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ToDoRepositoryModule {

    @Provides
    fun provideToDoRepository(
        appDatabase: AppDatabase,
    ): ToDoRepository {
        return ToDoRepository(appDatabase)
    }

}