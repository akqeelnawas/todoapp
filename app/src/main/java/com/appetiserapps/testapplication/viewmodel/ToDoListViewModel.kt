package com.appetiserapps.testapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appetiserapps.testapplication.model.ToDoItem
import com.appetiserapps.testapplication.usecase.CreateToDoItemUseCase
import com.appetiserapps.testapplication.usecase.FetchAllToDoItemsUseCase

class ToDoListViewModel: ViewModel() {

    private val createToDoItemUseCase = CreateToDoItemUseCase()

    private val fetchAllToDoItemsUseCase = FetchAllToDoItemsUseCase()

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

    private fun setToDoListItems(items: List<ToDoItem>) {
        _toDoItemsLiveData.value = items
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
