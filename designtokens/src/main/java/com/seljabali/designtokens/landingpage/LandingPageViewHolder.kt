package com.seljabali.designtokens.landingpage

import android.view.View
import android.widget.ImageView
import com.google.android.material.textview.MaterialTextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem

class LandingPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pageItemNameTextView: MaterialTextView = itemView.findViewById(R.id.pageItemNameTextView)
    private val pageItemIconImageView: ImageView = itemView.findViewById(R.id.pageItemIconImageView)

    fun bind(currentPageItem: LandingItem) {
        pageItemNameTextView.text = itemView.context.getString(currentPageItem.titleStringId)
        pageItemIconImageView.background = itemView.context.getDrawable(currentPageItem.iconId)
    }
}