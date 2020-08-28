package com.seljabali.designtokens.transparency

import android.view.View
import com.google.android.material.textview.MaterialTextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class TransparencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val transparencyView: View = itemView.findViewById(R.id.transparencyView)
    private val transparencyNameTextView: MaterialTextView = itemView.findViewById(R.id.transparencyNameTextView)
    private val transparencyValueTextView: MaterialTextView = itemView.findViewById(R.id.transparencyValueTextView)

    fun bind(transparency: Transparencies) {
        val context = itemView.context
        val transparencyValue: Float = Res.getFloat(context, transparency.transparencyId)
        transparencyNameTextView.text = Res.getString(context, transparency.stringId)
        transparencyNameTextView.setUnderlined()
        transparencyValueTextView.text = transparencyValue.toString()
        transparencyView.alpha = transparencyValue
        itemView.invalidate()
    }
}