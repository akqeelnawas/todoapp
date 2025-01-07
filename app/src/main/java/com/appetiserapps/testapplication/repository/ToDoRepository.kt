package com.appetiserapps.testapplication.repository

import com.appetiserapps.testapplication.model.ToDoItem

class ToDoRepository {

    private val items = arrayListOf<ToDoItem>()

    fun createItem(item: ToDoItem): Boolean {
        if (items.contains(item)) return false
        items.add(item)
        return items.contains(item)
    }

    fun getAllItems(): List<ToDoItem> = items.apply {
        add(ToDoItem(1, "Item 1"))
        add(ToDoItem(2, "Item 2"))
        add(ToDoItem(3, "Item 3"))
    }

    fun getCount() = items.size

}
