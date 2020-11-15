package com.seljabali.templateapplication.ui.weather.cityregionadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.seljabali.templateapplication.R

class CityRegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cityNameTextView: MaterialTextView = itemView.findViewById(R.id.city_name_text_view)
    private val regionNameTextView: MaterialTextView = itemView.findViewById(R.id.region_name_text_view)

    fun bind(cityRegion: CityRegion) {
        cityNameTextView.text = cityRegion.cityName
        regionNameTextView.text = cityRegion.regionName
        itemView.invalidate()
    }
}