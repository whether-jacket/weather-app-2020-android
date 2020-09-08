package com.seljabali.templateapplication.ui.addcity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.templateapplication.R

class CitySearchAdapter : RecyclerView.Adapter<CitySearchViewHolder>() {

    private val cityResults: ArrayList<CityResult> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CitySearchViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_city_search_result, parent, false
                    )
            )

    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        val city = cityResults[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cityResults.count()

    fun setCityResults(results: List<CityResult>) {
        cityResults.clear()
        cityResults.addAll(results)
        notifyDataSetChanged()
    }
}
