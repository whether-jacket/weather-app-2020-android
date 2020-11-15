package com.seljabali.templateapplication.ui.weather.cityregionadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.templateapplication.R

class CityRegionAdapter : RecyclerView.Adapter<CityRegionViewHolder>() {

    private var cityRegions: ArrayList<CityRegion> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityRegionViewHolder =
        CityRegionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city_region, parent, false
            )
        )

    override fun getItemCount(): Int = cityRegions.size

    override fun onBindViewHolder(holder: CityRegionViewHolder, position: Int) {
        val cityName = getItem(position)
        holder.bind(cityName)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setCityRegions(values: List<CityRegion>) {
        cityRegions.clear()
        cityRegions.addAll(values)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): CityRegion = cityRegions[position]
}