package com.seljabali.designtokens.cornerradiuses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class CornerRadiusAdapter : RecyclerView.Adapter<CornerRadiusViewHolder>() {

    private var cornerRadiusess: Array<CornerRadiuses> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CornerRadiusViewHolder =
        CornerRadiusViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_corner_radius, parent, false
            ))

    override fun getItemCount(): Int =  cornerRadiusess.size

    override fun onBindViewHolder(holder: CornerRadiusViewHolder, position: Int) {
        val cornerRadius = getItem(position)
        holder.bind(cornerRadius)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setCornerRadiuses(values: Array<CornerRadiuses>) {
        cornerRadiusess = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): CornerRadiuses = cornerRadiusess[position]
}