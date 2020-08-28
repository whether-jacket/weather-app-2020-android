package com.seljabali.designtokens.spacings.verticalspacings

import android.view.View
import com.google.android.material.textview.MaterialTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class VerticalSpacingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val verticalSpacingNameTextView: MaterialTextView = itemView.findViewById(R.id.verticalSpacingNameTextView)
    private val verticalSpacingDpValueTextView: MaterialTextView = itemView.findViewById(R.id.verticalSpacingDpValueTextView)
    private val verticalSpacingPxValueTextView: MaterialTextView = itemView.findViewById(R.id.verticalSpacingPxValueTextView)
    private val verticalSpacingTopPadding: View = itemView.findViewById(R.id.verticalSpacingTopPadding)
    private val verticalSpacingValue: View = itemView.findViewById(R.id.verticalSpacingValue)
    private val verticalSpacingBottomPadding: View = itemView.findViewById(R.id.verticalSpacingBottomPadding)

    fun bind(verticalSpacing: VerticalSpacings) {
        val context = itemView.context
        verticalSpacingNameTextView.text = Res.getString(context, verticalSpacing.stringId)
        verticalSpacingNameTextView.setUnderlined()

        verticalSpacingDpValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.dp), Res.getDp(context, verticalSpacing.spacingId).toString())
        verticalSpacingPxValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.px), Res.getPx(context, verticalSpacing.spacingId).toString())
        val layoutParams: ConstraintLayout.LayoutParams = verticalSpacingValue.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.height = Res.getRoundedPx(context, verticalSpacing.spacingId)
        verticalSpacingValue.layoutParams = layoutParams
        itemView.invalidate()
    }
}