package com.seljabali.designtokens.cornerradiuses

import android.view.View
import com.google.android.material.textview.MaterialTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class CornerRadiusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cornerRadiusNameTextView: MaterialTextView = itemView.findViewById(R.id.cornerRadiusNameTextView)
    private val cornerRadiusDpValueTextView: MaterialTextView = itemView.findViewById(R.id.cornerRadiusDpValueTextView)
    private val cornerRadiusPxValueTextView: MaterialTextView = itemView.findViewById(R.id.cornerRadiusPxValueTextView)
    private val cornerRadiusView: CardView = itemView.findViewById(R.id.cornerRadiusView)

    fun bind(cornerRadius: CornerRadiuses) {
        val context = itemView.context
        cornerRadiusNameTextView.text = Res.getString(context, cornerRadius.stringId)
        cornerRadiusNameTextView.setUnderlined()

        cornerRadiusDpValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.dp), Res.getDp(context, cornerRadius.spacingId).toString())
        cornerRadiusPxValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.px), Res.getPx(context, cornerRadius.spacingId).toString())
        cornerRadiusView.radius = Res.getPx(context, cornerRadius.spacingId)
        itemView.invalidate()
    }
}