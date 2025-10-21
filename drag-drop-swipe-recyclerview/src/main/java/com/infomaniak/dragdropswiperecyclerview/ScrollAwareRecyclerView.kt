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
package com.infomaniak.dragdropswiperecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.infomaniak.dragdropswiperecyclerview.listener.OnListScrollListener

/**
 * Extension of RecyclerView that detects when the user scrolls.
 */
open class ScrollAwareRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    /**
     * Listener for the scrolling events.
     */
    var scrollListener: OnListScrollListener? = null

    private val internalListScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            when (newState) {
                SCROLL_STATE_IDLE ->
                    scrollListener?.onListScrollStateChanged(OnListScrollListener.ScrollState.IDLE)

                SCROLL_STATE_DRAGGING ->
                    scrollListener?.onListScrollStateChanged(OnListScrollListener.ScrollState.DRAGGING)

                SCROLL_STATE_SETTLING ->
                    scrollListener?.onListScrollStateChanged(OnListScrollListener.ScrollState.SETTLING)
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            when {
                dy > 0 ->
                    scrollListener?.onListScrolled(OnListScrollListener.ScrollDirection.DOWN, dy)

                dy < 0 ->
                    scrollListener?.onListScrolled(OnListScrollListener.ScrollDirection.UP, -dy)

                dx > 0 ->
                    scrollListener?.onListScrolled(OnListScrollListener.ScrollDirection.RIGHT, dx)

                dx < 0 ->
                    scrollListener?.onListScrolled(OnListScrollListener.ScrollDirection.LEFT, -dx)
            }
        }
    }

    init {
        super.addOnScrollListener(internalListScrollListener)
    }

    @Deprecated("Use the property scrollListener instead.", ReplaceWith("scrollListener"))
    override fun addOnScrollListener(listener: OnScrollListener) {
        throw UnsupportedOperationException(
            "Only the property scrollListener can be used to add a scroll listener here."
        )
    }
}
