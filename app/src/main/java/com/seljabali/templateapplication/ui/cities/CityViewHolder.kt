package com.seljabali.templateapplication.ui.cities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.seljabali.templateapplication.R

class CityViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
    val cityNameTextView: TextView = itemView.findViewById(R.id.city_text_view)
    val dragIconImageView: ImageView = itemView.findViewById(R.id.drag_icon_image_view)
}