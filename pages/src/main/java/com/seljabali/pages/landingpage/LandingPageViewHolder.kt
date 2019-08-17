package com.seljabali.pages.landingpage

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.pages.R

class LandingPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pageItemNameTextView: TextView = itemView.findViewById(R.id.pageItemNameTextView)
    private val pageItemIconImageView: ImageView = itemView.findViewById(R.id.pageItemIconImageView)

    fun bind(currentPageItem: LandingItem) {
        pageItemNameTextView.text = itemView.context.getString(currentPageItem.titleStringId)
        pageItemIconImageView.background = itemView.context.getDrawable(currentPageItem.iconId)
    }
}