package com.seljabali.templateapplication.ui.cities

import android.view.View
import android.widget.ImageView
import com.google.android.material.textview.MaterialTextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.seljabali.templateapplication.R

class CityViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
    val cityNameTextView: MaterialTextView = itemView.findViewById(R.id.city_text_view)
    val dragIconImageView: ImageView = itemView.findViewById(R.id.drag_icon_image_view)
}