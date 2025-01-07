package com.appetiserapps.testapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.appetiserapps.testapplication.databinding.ActivityTodoListBinding
import com.appetiserapps.testapplication.extension.showShortToast
import com.appetiserapps.testapplication.viewmodel.ToDoListViewModel
import com.appetiserapps.testapplication.viewmodel.ToDoListViewModelFactory

class ToDoListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    private val viewModel: ToDoListViewModel by viewModels<ToDoListViewModel> {
        ToDoListViewModelFactory()
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

}
