package com.test.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.todoapp.model.ToDoItem
import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.usecase.CreateToDoItemUseCase
import com.test.todoapp.usecase.FetchAllToDoItemsUseCase
import com.test.todoapp.usecase.RemoveToDoItemUseCase

class ToDoListViewModel: ViewModel() {

    private val repository = ToDoRepository()

    private val createToDoItemUseCase = CreateToDoItemUseCase(repository)

    private val fetchAllToDoItemsUseCase = FetchAllToDoItemsUseCase(repository)

    private val removeToDoItemUseCase = RemoveToDoItemUseCase(repository)

    private val _toDoItemsLiveData = MutableLiveData<List<ToDoItem>>(arrayListOf())
    val toDoItemsLiveData: LiveData<List<ToDoItem>> = _toDoItemsLiveData

    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData

    fun init() {
        setToDoListItems(fetchAllToDoItemsUseCase())
    }

    fun onSubmitClicked(name: String) {
        val item = createToDoItemUseCase(name)
        if (item != null) {
            val items = getToDoListItems()
            items.add(0, item)
            setToDoListItems(items)
        } else {
            setErrorMessage("Failed to create item: $name")
        }
    }

    fun removeItemAt(position: Int) {
        val items = getToDoListItems()
        val item = items[position]
        val status = removeToDoItemUseCase(item)
        if (status) {
            items.remove(item)
            setToDoListItems(ArrayList(items))
        } else {
            setErrorMessage("Failed to remove item: ${item.name}")
        }
    }

    private fun setToDoListItems(items: List<ToDoItem>) {
        _toDoItemsLiveData.value = items
    }

    private fun getToDoListItems(): ArrayList<ToDoItem> {
        return _toDoItemsLiveData.value as ArrayList<ToDoItem>? ?: arrayListOf()
    }

    private fun setErrorMessage(message: String) {
        _errorMessageLiveData.value = message
    }

}

class ToDoListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoListViewModel::class.java)) {
            return ToDoListViewModel() as T
        }
        throw IllegalArgumentException("Unsupported `modelClass`: ${modelClass.name}")
    }
}
