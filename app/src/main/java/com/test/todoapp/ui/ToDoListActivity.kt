package com.test.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.test.todoapp.ToDoApp
import com.test.todoapp.databinding.ActivityTodoListBinding
import com.test.todoapp.extension.showShortToast
import com.test.todoapp.repository.ToDoRepository
import com.test.todoapp.viewmodel.ToDoListViewModel
import com.test.todoapp.viewmodel.ToDoListViewModelFactory

class ToDoListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    private val viewModel: ToDoListViewModel by viewModels<ToDoListViewModel> {
        ToDoListViewModelFactory(repository = ToDoRepository(getToDoApp().appDatabase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeLiveData()
        setupRecyclerView()
        setupCtas()
        viewModel.init()
    }

    private fun observeLiveData() {
        viewModel.toDoItemsLiveData.observe(this) {
            getRecyclerViewAdapter().setItems(it)
        }
        viewModel.errorMessageLiveData.observe(this) {
            showShortToast(it)
        }
    }

    private fun setupRecyclerView() = binding.rvToDoList.apply {
        adapter = ToDoListAdapter(layoutInflater)
        layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        ItemTouchHelper(RecyclerViewSwipeCallback(context) {
            viewModel.removeItemAt(it)
        }).attachToRecyclerView(this)
    }

    private fun setupCtas() {
        binding.btnSubmit.setOnClickListener {
            viewModel.onSubmitClicked(getInputToDoName())
            clearInputToDoName()
        }
    }

    private fun clearInputToDoName() {
        binding.etInput.setText("")
    }

    private fun getInputToDoName(): String =
        binding.etInput.text.toString()

    private fun getRecyclerViewAdapter() =
        binding.rvToDoList.adapter as ToDoListAdapter

    private fun getToDoApp() = application as ToDoApp

}
