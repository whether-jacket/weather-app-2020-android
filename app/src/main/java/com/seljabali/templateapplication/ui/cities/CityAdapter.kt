package com.seljabali.templateapplication.ui.cities

import android.view.View
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.seljabali.database.models.LocationDb

class CityAdapter(dataSet: List<LocationDb>) : DragDropSwipeAdapter<LocationDb, CityViewHolder>(dataSet) {

    override fun getViewHolder(itemLayout: View) = CityViewHolder(itemLayout)

    override fun onBindViewHolder(item: LocationDb, viewHolder: CityViewHolder, position: Int) {
        viewHolder.cityNameTextView.text = item.cityName
    }

    override fun getViewToTouchToStartDraggingItem(
        item: LocationDb,
        viewHolder: CityViewHolder,
        position: Int
    ): View? = viewHolder.dragIconImageView
}
