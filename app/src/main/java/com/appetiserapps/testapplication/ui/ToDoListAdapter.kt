package com.appetiserapps.testapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appetiserapps.testapplication.databinding.RowTodoListBinding
import com.appetiserapps.testapplication.model.ToDoItem

class ToDoListAdapter(
    private val layoutInflater: LayoutInflater,
): RecyclerView.Adapter<ToDoListAdapterViewHolder>() {

    private val toDoItems = arrayListOf<ToDoItem>()

    fun setItems(items: List<ToDoItem>) {
        val oldItems = ArrayList(toDoItems)
        toDoItems.clear()
        toDoItems.addAll(items)
        val diffUtilCallback = ToDoListDiffUtilCallback(oldItems, items)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        diff.dispatchUpdatesTo(this@ToDoListAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapterViewHolder {
        val binding = RowTodoListBinding.inflate(layoutInflater, parent, false)
        return ToDoListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoListAdapterViewHolder, position: Int) {
        val item = toDoItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = toDoItems.size

}

class ToDoListAdapterViewHolder(
    private val binding: RowTodoListBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ToDoItem) {
        binding.todoItemName.text = item.name
    }

}
