package com.seljabali.templateapplication.ui.cities

import android.view.View
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter

class CityAdapter(dataSet: List<String>) : DragDropSwipeAdapter<String, CityViewHolder>(dataSet) {

    override fun getViewHolder(itemLayout: View) = CityViewHolder(itemLayout)

    override fun onBindViewHolder(item: String, viewHolder: CityViewHolder, position: Int) {
        viewHolder.cityNameTextView.text = item
    }

    override fun getViewToTouchToStartDraggingItem(
        item: String,
        viewHolder: CityViewHolder,
        position: Int
    ): View? {
        return viewHolder.dragIconImageView
    }
}
