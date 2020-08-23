package com.seljabali.designtokens.spacings.padding

import android.view.View
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class PaddingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val paddingNameTextView: TextView = itemView.findViewById(R.id.paddingNameTextView)
    private val paddingDpValueTextView: TextView = itemView.findViewById(R.id.paddingDpValueTextView)
    private val paddingPxValueTextView: TextView = itemView.findViewById(R.id.paddingPxValueTextView)
    private val paddingValueTextView: TextView = itemView.findViewById(R.id.paddingValueTextView)

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