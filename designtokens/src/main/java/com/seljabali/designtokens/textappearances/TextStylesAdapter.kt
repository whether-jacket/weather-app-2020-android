package com.seljabali.designtokens.textappearances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R

class TextStylesAdapter : RecyclerView.Adapter<TextStyleViewHolder>() {

    private var textStyles: Array<TextStyles> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextStyleViewHolder =
        TextStyleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_text_style, parent, false
            )
        )

    override fun getItemCount(): Int =  textStyles.size

    override fun onBindViewHolder(holder: TextStyleViewHolder, position: Int) {
        val textStylesAtPosition = getItem(position)
        holder.bind(textStylesAtPosition)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun setTextStyles(values: Array<TextStyles>) {
        textStyles = values
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): TextStyles = textStyles[position]
}