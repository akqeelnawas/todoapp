package com.test.todoapp.repository

import com.test.todoapp.model.ToDoItem

class ToDoRepository {

    private val items = arrayListOf<ToDoItem>()

    fun createItem(item: ToDoItem): Boolean {
        if (items.contains(item)) return false
        items.add(item)
        return items.contains(item)
    }

    fun removeItem(item: ToDoItem): Boolean {
        if (items.contains(item)) {
            items.remove(item)
            return true
        }
        return false
    }

    fun getAllItems(): List<ToDoItem> = ArrayList(items)

    fun getCount() = items.size

}
