package com.seljabali.designtokens.colors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class ColorsAdapter: RecyclerView.Adapter<ColorsViewHolder>() {

    private var colors: Array<Colors> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder =
        ColorsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_color, parent, false
            ))

    override fun getItemCount(): Int =  colors.size

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val color = getItem(position)
        holder.bind(color)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setColors(values: Array<Colors>) {
        colors = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Colors = colors[position]
}