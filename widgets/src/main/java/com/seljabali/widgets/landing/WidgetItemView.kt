package com.seljabali.widgets.landing

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.seljabali.widgets.R

class WidgetItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvWidgetName = itemView.findViewById<MaterialTextView>(R.id.tv_widget_name)
    val ivWidgetPic = itemView.findViewById<ImageView>(R.id.iv_widget_pic)
}