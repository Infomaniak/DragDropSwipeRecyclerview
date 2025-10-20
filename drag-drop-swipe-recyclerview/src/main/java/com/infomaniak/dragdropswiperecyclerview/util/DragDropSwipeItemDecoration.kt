/*
 * Infomaniak Drag/Drop/Swipe RecyclerView - Android
 * Copyright (C) 2018 Julio Ernesto Rodríguez Cabañas
 * Copyright (C) 2025 Infomaniak Network SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.infomaniak.dragdropswiperecyclerview.util

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.infomaniak.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.infomaniak.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.infomaniak.dragdropswiperecyclerview.DragDropSwipeRecyclerView.ListOrientation

internal class DragDropSwipeItemDecoration(var divider: Drawable) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent is DragDropSwipeRecyclerView) {
            for (index in 0 until parent.childCount) {
                val child = parent.getChildAt(index)

                // We only draw dividers for items that are not being moved (moving ones will draw their own).
                // The reason why we need to do it this way is because this method is not called as often on
                // items that are being moved, so if we use it to draw the dividers, some frames will be lost
                // and even some spacing may appear between the divider and the item layout that is moving.
                // Luckily, some of the methods that are called by the system on moving items include a canvas
                // as a parameter and are called often enough to allow us to draw the dividers without lag.
                // The joys of programming for Android!
                if (!itemIsBeingMoved(parent, child))
                    when (parent.orientation) {
                        ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING,
                        ListOrientation.VERTICAL_LIST_WITH_UNCONSTRAINED_DRAGGING ->
                            drawHorizontalDividers(child, c, divider)

                        ListOrientation.HORIZONTAL_LIST_WITH_UNCONSTRAINED_DRAGGING,
                        ListOrientation.HORIZONTAL_LIST_WITH_HORIZONTAL_DRAGGING ->
                            drawVerticalDividers(child, c, divider)

                        ListOrientation.GRID_LIST_WITH_HORIZONTAL_SWIPING,
                        ListOrientation.GRID_LIST_WITH_VERTICAL_SWIPING -> {
                            drawHorizontalDividers(child, c, divider)
                            drawVerticalDividers(child, c, divider)
                        }

                        null -> {}
                    }
            }
        } else throw TypeCastException("The recycler view must be an extension of DragDropSwipeRecyclerView.")
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent is DragDropSwipeRecyclerView) {

            val position = parent.getChildAdapterPosition(view)
            if (position != 0) {
                when (parent.orientation) {
                    ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING,
                    ListOrientation.VERTICAL_LIST_WITH_UNCONSTRAINED_DRAGGING ->
                        outRect.top = divider.intrinsicHeight

                    ListOrientation.HORIZONTAL_LIST_WITH_UNCONSTRAINED_DRAGGING,
                    ListOrientation.HORIZONTAL_LIST_WITH_HORIZONTAL_DRAGGING ->
                        outRect.left = divider.intrinsicWidth

                    ListOrientation.GRID_LIST_WITH_HORIZONTAL_SWIPING,
                    ListOrientation.GRID_LIST_WITH_VERTICAL_SWIPING -> {
                        if (position >= parent.numOfColumnsPerRowInGridList)
                            outRect.top = divider.intrinsicHeight

                        if (position >= parent.numOfRowsPerColumnInGridList)
                            outRect.left = divider.intrinsicWidth
                    }

                    null -> {}
                }
            }
        } else throw TypeCastException("The recycler view must be an extension of AsyncSwipeRecyclerView.")
    }

    private fun itemIsBeingMoved(parent: RecyclerView, child: View): Boolean {
        val viewHolder = parent.getChildViewHolder(child) as DragDropSwipeAdapter.ViewHolder

        return viewHolder.isBeingDragged || viewHolder.isBeingSwiped
    }
}
