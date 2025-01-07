package com.test.todoapp.ui

import android.R
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSwipeCallback(
    private val context: Context,
    private val onSwiped: (position: Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val paint by lazy {
        Paint().apply {
            setColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
        }
    }

    private val paint2 by lazy {
        Paint().apply {
            setColor(ContextCompat.getColor(context, android.R.color.black))
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false // no-op
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView

        val posLeft = itemView.right.toFloat()
        val posRight = posLeft + dX
        val posTop = itemView.top.toFloat()
        val posBottom = itemView.bottom.toFloat()

        c.drawRect(RectF(posLeft, posTop, posRight, posBottom), paint)

        if (dX < 0) {
            val icon = BitmapFactory.decodeResource(
                context.resources,
                android.R.drawable.ic_menu_delete
            )
            val iconPosH = itemView.right - icon.width
            val iconPosV = ((itemView.top + itemView.bottom) / 2) - (icon.height / 2)
            c.drawBitmap(icon, iconPosH.toFloat(), iconPosV.toFloat(), paint)
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwiped(viewHolder.adapterPosition)
    }
}
