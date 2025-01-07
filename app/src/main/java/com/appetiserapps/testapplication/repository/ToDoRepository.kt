package com.appetiserapps.testapplication.repository

import com.appetiserapps.testapplication.model.ToDoItem

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
        return true
    }

    fun getAllItems(): List<ToDoItem> = items

    fun getCount() = items.size

}
