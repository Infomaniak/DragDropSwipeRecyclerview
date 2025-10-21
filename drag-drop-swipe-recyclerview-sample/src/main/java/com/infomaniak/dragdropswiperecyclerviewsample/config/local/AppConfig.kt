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
package com.infomaniak.dragdropswiperecyclerviewsample.config.local

enum class ListFragmentType(val index: Int, val tag: String) {
    VERTICAL(0, "VerticalFragment"),
    HORIZONTAL(1, "HorizontalFragment"),
    GRID(2, "GridFragment"),
}

data class ListFragmentConfig(
    var isUsingStandardItemLayout: Boolean,
    var isRestrictingDraggingDirections: Boolean,
    var isDrawingBehindSwipedItems: Boolean,
    var isUsingFadeOnSwipedItems: Boolean,
)

private val listFragmentConfigurations = listOf(

    // Initial state of the vertical-list fragment
    ListFragmentConfig(
        isUsingStandardItemLayout = true,
        isRestrictingDraggingDirections = true,
        isDrawingBehindSwipedItems = true,
        isUsingFadeOnSwipedItems = false,
    ),

    // Initial state of the horizontal-list fragment
    ListFragmentConfig(
        isUsingStandardItemLayout = false,
        isRestrictingDraggingDirections = false,
        isDrawingBehindSwipedItems = true,
        isUsingFadeOnSwipedItems = true,
    ),

    // Initial state of the grid-list fragment
    ListFragmentConfig(
        isUsingStandardItemLayout = false,
        isRestrictingDraggingDirections = false,
        isDrawingBehindSwipedItems = true,
        isUsingFadeOnSwipedItems = true,
    )
)

var currentListFragmentType = ListFragmentType.VERTICAL
val currentListFragmentConfig
    get() = listFragmentConfigurations[currentListFragmentType.index]
