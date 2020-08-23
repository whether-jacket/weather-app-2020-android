package com.seljabali.designtokens.transparency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class TransparencyAdapter : RecyclerView.Adapter<TransparencyViewHolder>() {

    private var transparencies: Array<Transparencies> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransparencyViewHolder =
        TransparencyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_transparency, parent, false
            ))

    override fun getItemCount(): Int =  transparencies.size

    override fun onBindViewHolder(holder: TransparencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setTransparencies(values: Array<Transparencies>) {
        transparencies = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Transparencies = transparencies[position]
}