package com.seljabali.designtokens.letterspacings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class LetterSpacingsAdapter : RecyclerView.Adapter<LetterSpacingsViewHolder>() {

    private var letterSpacings: Array<LetterSpacings> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterSpacingsViewHolder =
        LetterSpacingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_letter_spacing, parent, false
            ))

    override fun getItemCount(): Int =  letterSpacings.size

    override fun onBindViewHolder(holder: LetterSpacingsViewHolder, position: Int) {
        val letterSpacingAtPosition = getItem(position)
        holder.bind(letterSpacingAtPosition)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setLetterSpacings(values: Array<LetterSpacings>) {
        letterSpacings = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): LetterSpacings = letterSpacings[position]
}