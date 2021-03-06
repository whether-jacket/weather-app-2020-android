package com.seljabali.designtokens.colors

import android.view.View
import android.view.ViewGroup
import com.google.android.material.textview.MaterialTextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.designtokens.R

class ColorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemColorViewGroup: ViewGroup = itemView.findViewById(R.id.itemColorViewGroup)
    private val colorNameTextView: MaterialTextView = itemView.findViewById(R.id.colorNameTextView)
    private val colorHexValueTextView: MaterialTextView = itemView.findViewById(R.id.colorHexValueTextView)

    fun bind(color: Colors) {
        val context = itemView.context
        itemColorViewGroup.setBackgroundColor(Res.getColor(context, color.colorId))
        colorNameTextView.text = Res.getIdentifier(context, color.colorId)!!.replace("_", " ")

        colorHexValueTextView.text = Res.getColorHex(context, color.colorId)
        itemView.invalidate()
    }
}