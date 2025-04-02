package com.infomaniak.dragdropswiperecyclerviewsample.data.model

import java.util.UUID

class IceCream(
    val uuid: UUID,
    val name: String,
    val price: Float,
    val colorRed: Float,
    val colorGreen: Float,
    val colorBlue: Float,
) {
    override fun toString() = name
}
