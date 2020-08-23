package com.seljabali.designtokens.elevations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class ElevationsAdapter : RecyclerView.Adapter<ElevationViewHolder>() {

    private var elevations: Array<Elevations> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElevationViewHolder =
        ElevationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_elevation, parent, false
            ))

    override fun getItemCount(): Int =  elevations.size

    override fun onBindViewHolder(holder: ElevationViewHolder, position: Int) {
        val elevation = getItem(position)
        holder.bind(elevation)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setElevations(values: Array<Elevations>) {
        elevations = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Elevations = elevations[position]
}