package com.appetiserapps.testapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSwipeCallback(
    private val context: Context,
    private val onSwiped: (position: Int) -> Unit,
): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val paint by lazy {
        Paint().apply {
            setColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
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
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwiped(viewHolder.adapterPosition)
    }

}
