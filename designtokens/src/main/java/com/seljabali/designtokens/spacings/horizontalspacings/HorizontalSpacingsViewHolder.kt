package com.seljabali.designtokens.spacings.horizontalspacings

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class HorizontalSpacingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val horizontalSpacingNameTextView: TextView = itemView.findViewById(R.id.horizontalSpacingNameTextView)
    private val horizontalSpacingDpValueTextView: TextView = itemView.findViewById(R.id.horizontalSpacingDpValueTextView)
    private val horizontalSpacingPxValueTextView: TextView = itemView.findViewById(R.id.horizontalSpacingPxValueTextView)
    private val horizontalSpacingTopPadding: View = itemView.findViewById(R.id.horizontalSpacingStartPadding)
    private val horizontalSpacingValue: View = itemView.findViewById(R.id.horizontalSpacingValue)
    private val horizontalSpacingBottomPadding: View = itemView.findViewById(R.id.horizontalSpacingEndPadding)

    fun bind(horizontalSpacing: HorizontalSpacings) {
        val context = itemView.context
        horizontalSpacingNameTextView.text = Res.getString(context, horizontalSpacing.stringId)
        horizontalSpacingNameTextView.setUnderlined()

        horizontalSpacingDpValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.dp), Res.getDp(context, horizontalSpacing.spacingId).toString())
        horizontalSpacingPxValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.px), Res.getPx(context, horizontalSpacing.spacingId).toString())
        val layoutParams: ConstraintLayout.LayoutParams = horizontalSpacingValue.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.width = Res.getRoundedPx(context, horizontalSpacing.spacingId)
        horizontalSpacingValue.layoutParams = layoutParams
        itemView.invalidate()
    }
}