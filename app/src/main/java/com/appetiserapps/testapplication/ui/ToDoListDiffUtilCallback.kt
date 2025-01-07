package com.appetiserapps.testapplication.ui

import androidx.recyclerview.widget.DiffUtil
import com.appetiserapps.testapplication.model.ToDoItem

class ToDoListDiffUtilCallback(
    private val oldList: List<ToDoItem>,
    private val newList: List<ToDoItem>,
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem === newListItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem == newListItem
    }

}