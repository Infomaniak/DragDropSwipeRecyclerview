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
 * Listener for the swiping events of list items.
 */
interface OnItemSwipeListener<T> {

    /**
     * Indicates the direction in which the swipe action is performed.
     */
    enum class SwipeDirection {
        RIGHT_TO_LEFT,
        LEFT_TO_RIGHT,
        DOWN_TO_UP,
        UP_TO_DOWN
    }

    /**
     * Callback for whenever an item has been swiped.
     * If it returns false, this event will be considered as not handled and the swiped item will be
     * removed from the adapter's data set. This is the standard behaviour.
     * If it returns true, this event will be considered as handled and the adapter's data set will
     * not be changed. In this case, it is your responsibility to apply the necessary changes to the
     * adapter's data set.
     *
     * @param position The position of the swiped item.
     * @param direction The direction in which the item has been swiped.
     * @param item The item that has been swiped.
     * @return True if the event was handled (i.e., if the necessary changes were applied to the
     * adapter's data set within the callback); false otherwise. If false, the swiped item will be
     * removed from the adapter's data set automatically.
     */
    fun onItemSwiped(position: Int, direction: SwipeDirection, item: T): Boolean
}
