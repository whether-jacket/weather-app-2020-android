package com.seljabali.designtokens.spacings.verticalspacings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class VerticalSpacingsAdapter : RecyclerView.Adapter<VerticalSpacingsViewHolder>() {

    private var verticalSpacings: Array<VerticalSpacings> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalSpacingsViewHolder =
        VerticalSpacingsViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_vertical_spacing, parent, false
        ))

    override fun getItemCount(): Int =  verticalSpacings.size

    override fun onBindViewHolder(holder: VerticalSpacingsViewHolder, position: Int) {
        val verticalSpacingAtPosition = getItem(position)
        holder.bind(verticalSpacingAtPosition)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setVerticalSpacings(values: Array<VerticalSpacings>) {
        verticalSpacings = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): VerticalSpacings = verticalSpacings[position]
}