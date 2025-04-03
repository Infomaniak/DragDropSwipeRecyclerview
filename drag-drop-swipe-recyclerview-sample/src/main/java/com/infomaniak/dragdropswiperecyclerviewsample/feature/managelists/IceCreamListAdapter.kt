package com.infomaniak.dragdropswiperecyclerviewsample.feature.managelists

import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.infomaniak.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.infomaniak.dragdropswiperecyclerviewsample.R
import com.infomaniak.dragdropswiperecyclerviewsample.data.model.IceCream
import com.infomaniak.dragdropswiperecyclerviewsample.util.Logger

/**
 * Adapter for a list of ice creams.
 */
class IceCreamListAdapter(
    dataSet: List<IceCream> = emptyList(),
) : DragDropSwipeAdapter<IceCream, IceCreamListAdapter.ViewHolder>(dataSet) {

    class IceCreamDiffUtil : DiffUtil.ItemCallback<IceCream>() {

        override fun areItemsTheSame(oldItem: IceCream, newItem: IceCream): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: IceCream, newItem: IceCream): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.price == newItem.price &&
                    oldItem.colorRed == newItem.colorRed &&
                    oldItem.colorGreen == newItem.colorGreen &&
                    oldItem.colorBlue == newItem.colorBlue
        }
    }

    override val asyncListDiffer: AsyncListDiffer<IceCream> = AsyncListDiffer(this, IceCreamDiffUtil())

    class ViewHolder(iceCreamLayout: View) : DragDropSwipeAdapter.ViewHolder(iceCreamLayout) {
        val iceCreamNameView: TextView = itemView.findViewById(R.id.ice_cream_name)
        val iceCreamPriceView: TextView = itemView.findViewById(R.id.ice_cream_price)
        val dragIcon: ImageView = itemView.findViewById(R.id.drag_icon)
        val iceCreamIcon: ImageView? = itemView.findViewById(R.id.ice_cream_icon)
        val iceCreamPhotoFilter: View? = itemView.findViewById(R.id.ice_cream_photo_filter)
    }

    override fun getViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(item: IceCream, viewHolder: ViewHolder, position: Int) = with(viewHolder) {
        val context = itemView.context

        // Set ice cream name and price
        iceCreamNameView.text = item.name
        iceCreamPriceView.text = context.getString(R.string.priceFormat, item.price)

        // Set ice cream icon color
        val red = (item.colorRed * 255).toInt()
        val green = (item.colorGreen * 255).toInt()
        val blue = (item.colorBlue * 255).toInt()

        // Set the icon/image color
        when {
            iceCreamIcon != null -> {
                val iceCreamIconColor = Color.rgb(red, green, blue)
                ImageViewCompat.setImageTintList(iceCreamIcon, ColorStateList.valueOf(iceCreamIconColor))
            }
            iceCreamPhotoFilter != null -> {
                val iceCreamPhotoFilterColor = Color.argb(128, red, green, blue)
                iceCreamPhotoFilter.setBackgroundColor(iceCreamPhotoFilterColor)
            }
        }
    }

    override fun getViewToTouchToStartDraggingItem(item: IceCream, viewHolder: ViewHolder, position: Int) = viewHolder.dragIcon

    override fun onDragStarted(item: IceCream, viewHolder: ViewHolder) {
        Logger.log("Dragging started on ${item.name}")
    }

    override fun onSwipeStarted(item: IceCream, viewHolder: ViewHolder) {
        Logger.log("Swiping started on ${item.name}")
    }

    override fun onIsDragging(
        item: IceCream?,
        viewHolder: ViewHolder,
        offsetX: Int,
        offsetY: Int,
        canvasUnder: Canvas?,
        canvasOver: Canvas?,
        isUserControlled: Boolean,
    ) {
        // Call commented out to avoid saturating the log
        // Logger.log("The ${if (isUserControlled) "User" else "System"} is dragging ${item.name} (offset X: $offsetX, offset Y: $offsetY)")
    }

    override fun onIsSwiping(
        item: IceCream?,
        viewHolder: ViewHolder,
        offsetX: Int,
        offsetY: Int,
        canvasUnder: Canvas?,
        canvasOver: Canvas?,
        isUserControlled: Boolean,
    ) {
        // Call commented out to avoid saturating the log
        // Logger.log("The ${if (isUserControlled) "User" else "System"} is swiping ${item?.name} (offset X: $offsetX, offset Y: $offsetY)")
    }

    override fun onDragFinished(item: IceCream, viewHolder: ViewHolder) {
        Logger.log("Dragging finished on ${item.name} (the item was dropped)")
    }

    override fun onSwipeAnimationFinished(viewHolder: ViewHolder) {
        Logger.log("Swiping animation finished")
    }
}
