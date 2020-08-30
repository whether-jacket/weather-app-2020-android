package com.seljabali.templateapplication.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.templateapplication.R

class CitiesAdapter: RecyclerView.Adapter<CityNameViewHolder>() {

    private var cityNames: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityNameViewHolder =
        CityNameViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city_name, parent, false
            ))

    override fun getItemCount(): Int =  cityNames.size

    override fun onBindViewHolder(holder: CityNameViewHolder, position: Int) {
        val cityName = getItem(position)
        holder.bind(cityName)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setCityNames(values: List<String>) {
        cityNames.clear()
        cityNames.addAll(values)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): String = cityNames[position]
}