package com.seljabali.widgets.landing

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.widgets.R

class WidgetItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvWidgetName = itemView.findViewById<TextView>(R.id.tv_widget_name)
    val ivWidgetPic = itemView.findViewById<ImageView>(R.id.iv_widget_pic)
}