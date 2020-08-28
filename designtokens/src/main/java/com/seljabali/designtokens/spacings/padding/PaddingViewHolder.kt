package com.seljabali.designtokens.spacings.padding

import android.view.View
import com.google.android.material.textview.MaterialTextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class PaddingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val paddingNameTextView: MaterialTextView = itemView.findViewById(R.id.paddingNameTextView)
    private val paddingDpValueTextView: MaterialTextView = itemView.findViewById(R.id.paddingDpValueTextView)
    private val paddingPxValueTextView: MaterialTextView = itemView.findViewById(R.id.paddingPxValueTextView)
    private val paddingValueTextView: MaterialTextView = itemView.findViewById(R.id.paddingValueTextView)

    fun bind(padding: Paddings) {
        val context = itemView.context
        paddingNameTextView.text = Res.getString(context, padding.stringId)
        paddingNameTextView.setUnderlined()

        paddingDpValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.dp), Res.getDp(context, padding.spacingId).toString())
        paddingPxValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.px), Res.getPx(context, padding.spacingId).toString())
        paddingValueTextView.setPadding(Res.getRoundedPx(context, padding.spacingId))
        itemView.invalidate()
    }
}