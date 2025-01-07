package com.test.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import com.test.todoapp.databinding.ActivityTodoListBinding
import com.test.todoapp.extension.showShortToast
import com.test.todoapp.model.ToDoItem
import com.test.todoapp.viewmodel.ToDoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    private val viewModel: ToDoListViewModel by viewModels()

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
            onToDoItemsChanged(it)
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
        binding.btnSubmitPlaceHolder.setOnClickListener {
            viewModel.onSubmitClicked(getInputToDoNamePlaceholder())
            clearInputToDoName()
        }
    }

    private fun clearInputToDoName() {
        binding.etInput.setText("")
        binding.etInputPlaceHolder.setText("")
    }

    private fun getInputToDoName(): String =
        binding.etInput.text.toString()

    private fun getInputToDoNamePlaceholder(): String =
        binding.etInputPlaceHolder.text.toString()

    private fun onToDoItemsChanged(items: List<ToDoItem>) {
        binding.placeHolderContainer.isVisible = items.isEmpty()
        binding.rvToDoList.isVisible = items.isNotEmpty()
        binding.etInput.isVisible = items.isNotEmpty()
        binding.btnSubmit.isVisible = items.isNotEmpty()
        binding.labelTitle.isVisible = items.isNotEmpty()
        getRecyclerViewAdapter().setItems(items)
    }

    private fun getRecyclerViewAdapter() =
        binding.rvToDoList.adapter as ToDoListAdapter

}
