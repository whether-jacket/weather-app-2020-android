package com.seljabali.pages.landingpage

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.seljabali.pages.R

class PagesLandingPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pageItemNameTextView: MaterialTextView = itemView.findViewById(R.id.pageItemNameTextView)
    private val pageItemIconImageView: ImageView = itemView.findViewById(R.id.pageItemIconImageView)

    fun bind(currentPageItem: LandingItem) {
        pageItemNameTextView.text = itemView.context.getString(currentPageItem.titleStringId)
        pageItemIconImageView.background = itemView.context.getDrawable(currentPageItem.iconId)
    }
}