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
 * Listener for the dragging events of list items.
 */
interface OnItemDragListener<T> {

    /**
     * Callback for whenever an item that is being dragged exchanges positions with another one.
     * It will be called every time an exchange occurs, no matter if the user is still dragging
     * the item or not.
     *
     * @param previousPosition The old position of the item that has just been exchanged with the
     *        dragged one.
     * @param newPosition The new position of the dragged item.
     * @param item The dragged item.
     */
    fun onItemDragged(previousPosition: Int, newPosition: Int, item: T)

    /**
     * Callback for when the drag & drop event has completed because the user has dropped the item.
     *
     * @param initialPosition The position of the item before the user started dragging it.
     * @param finalPosition The position in which the user has dropped the item.
     * @param item The dropped item.
     */
    fun onItemDropped(initialPosition: Int, finalPosition: Int, item: T)
}
