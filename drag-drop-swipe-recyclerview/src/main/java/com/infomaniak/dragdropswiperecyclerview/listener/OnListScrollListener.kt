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
package com.infomaniak.dragdropswiperecyclerview.listener

/**
 * Listener for the scroll events on the list.
 */
interface OnListScrollListener {

    /**
     * Indicates the direction in which the scroll action is performed.
     */
    enum class ScrollDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Indicates the state of the scroll.
     */
    enum class ScrollState {
        IDLE,
        DRAGGING,
        SETTLING
    }

    /**
     * Callback for whenever the list has been scrolled.
     *
     * @param scrollDirection The direction in which the list has been scrolled.
     * @param distance The distance in pixels that the list has been scrolled.
     */
    fun onListScrolled(scrollDirection: ScrollDirection, distance: Int)

    /**
     * Callback for whenever the scroll state of the list changes.
     *
     * @param scrollState The scroll state of the list.
     */
    fun onListScrollStateChanged(scrollState: ScrollState)
}
