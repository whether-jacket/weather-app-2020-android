package com.seljabali.designtokens.elevations

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R
import com.seljabali.designtokens.elevations.Elevations

class ElevationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val elevationNameTextView: TextView = itemView.findViewById(R.id.elevationNameTextView)
    private val elevationDpValueTextView: TextView = itemView.findViewById(R.id.elevationDpValueTextView)
    private val elevationPxValueTextView: TextView = itemView.findViewById(R.id.elevationPxValueTextView)
    private val elevationView: AppCompatButton = itemView.findViewById(R.id.elevationView)

    fun bind(elevation: Elevations) {
        val context = itemView.context
        elevationNameTextView.text = Res.getString(context, elevation.stringId)
        elevationNameTextView.setUnderlined()

        elevationDpValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.dp), Res.getDp(context, elevation.elevationId).toString())
        elevationPxValueTextView.text = Res.getStringFormatted(context, R.string.colon_formatted, Res.getString(context, R.string.px), Res.getPx(context, elevation.elevationId).toString())
        elevationView.elevation = Res.getPx(context, elevation.elevationId)
        itemView.invalidate()
    }
}