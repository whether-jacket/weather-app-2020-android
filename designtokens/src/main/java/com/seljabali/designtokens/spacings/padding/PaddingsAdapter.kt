package com.seljabali.designtokens.spacings.padding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class PaddingsAdapter : RecyclerView.Adapter<PaddingViewHolder>() {

    private var paddings: Array<Paddings> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaddingViewHolder =
        PaddingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_padding, parent, false
            ))

    override fun getItemCount(): Int = paddings.size

    override fun onBindViewHolder(holder: PaddingViewHolder, position: Int) {
        val verticalSpacingAtPosition = getItem(position)
        holder.bind(verticalSpacingAtPosition)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setPaddings(values: Array<Paddings>) {
        paddings = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Paddings = paddings[position]

}