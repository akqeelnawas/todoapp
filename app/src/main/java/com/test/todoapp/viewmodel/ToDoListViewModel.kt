package com.test.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.todoapp.model.ToDoItem
import com.test.todoapp.usecase.CreateToDoItemUseCase
import com.test.todoapp.usecase.FetchAllToDoItemsUseCase
import com.test.todoapp.usecase.RemoveToDoItemUseCase

class ToDoListViewModel: ViewModel() {

    private val createToDoItemUseCase = CreateToDoItemUseCase()

    private val fetchAllToDoItemsUseCase = FetchAllToDoItemsUseCase()

    private val removeToDoItemUseCase = RemoveToDoItemUseCase()

    private val _toDoItemsLiveData = MutableLiveData<List<ToDoItem>>(arrayListOf())
    val toDoItemsLiveData: LiveData<List<ToDoItem>> = _toDoItemsLiveData

    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData

    fun init() {
        setToDoListItems(fetchAllToDoItemsUseCase())
    }

    fun onSubmitClicked(name: String) {
        val item = createToDoItemUseCase.invoke(name)
        if (item != null) {
            val items = _toDoItemsLiveData.value as ArrayList<ToDoItem>
            items.add(0, item)
            setToDoListItems(items)
        } else {
            setErrorMessage("Failed to create item: $name")
        }
    }

    fun removeItemAt(position: Int) {
        val items = getToDoListItems()
        if (items.size > position) {
            val item = items[position]
            val status = removeToDoItemUseCase(item)
            if (status) {
                setToDoListItems(ArrayList(items).apply {
                    removeItemAt(position)
                })
            } else {
                setErrorMessage("Failed to remove item")
            }
        } else {
            setErrorMessage("Failed to remove item")
        }
    }

    private fun setToDoListItems(items: List<ToDoItem>) {
        _toDoItemsLiveData.value = items
    }

    private fun getToDoListItems(): List<ToDoItem> {
        return _toDoItemsLiveData.value ?: listOf()
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
