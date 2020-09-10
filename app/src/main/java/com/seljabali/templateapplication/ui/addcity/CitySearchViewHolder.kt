package com.seljabali.templateapplication.ui.addcity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.seljabali.templateapplication.R

class CitySearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val cityNameTextView: MaterialTextView = itemView.findViewById(R.id.city_name_text_view)
    val regionNameTextView: MaterialTextView = itemView.findViewById(R.id.region_name_text_view)

    fun bind(city: CityResult) {
        cityNameTextView.text = city.cityName
        regionNameTextView.text = city.regionName
    }

}
