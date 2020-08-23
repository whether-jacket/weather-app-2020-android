package com.seljabali.designtokens.spacings.horizontalspacings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class HorizontalSpacingsAdapter : RecyclerView.Adapter<HorizontalSpacingsViewHolder>() {

    private var horizontalSpacings: Array<HorizontalSpacings> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalSpacingsViewHolder =
        HorizontalSpacingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_horizontal_spacing, parent, false
            ))

    override fun getItemCount(): Int =  horizontalSpacings.size

    override fun onBindViewHolder(holder: HorizontalSpacingsViewHolder, position: Int) {
        val verticalSpacingAtPosition = getItem(position)
        holder.bind(verticalSpacingAtPosition)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setVerticalSpacings(values: Array<HorizontalSpacings>) {
        horizontalSpacings = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): HorizontalSpacings = horizontalSpacings[position]
}