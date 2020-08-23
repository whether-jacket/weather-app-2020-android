package com.seljabali.designtokens.letterspacings

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.core.utilities.Res
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.designtokens.R

class LetterSpacingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val letterSpacingsNameTextView: TextView = itemView.findViewById(R.id.letterSpacingsNameTextView)
    private val letterSpacingsActualValueTextView: TextView = itemView.findViewById(R.id.letterSpacingsActualValueTextView)
    private val letterSpacingsValueTextView: TextView = itemView.findViewById(R.id.letterSpacingsValueTextView)

    fun bind(letterSpacing: LetterSpacings) {
        val context = itemView.context
        letterSpacingsNameTextView.text = Res.getString(context, letterSpacing.stringId)
        letterSpacingsNameTextView.setUnderlined()

        letterSpacingsActualValueTextView.text = Res.getFloat(context, letterSpacing.floatId).toString()
        letterSpacingsValueTextView.letterSpacing = Res.getFloat(context, letterSpacing.floatId)
        itemView.invalidate()
    }
}