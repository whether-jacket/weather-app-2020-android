package com.seljabali.templateapplication.ui.weather

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.seljabali.templateapplication.R

class CityNameViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cityNameTextView: MaterialTextView = itemView.findViewById(R.id.city_name_text_view)

    fun bind(name: String) {
        val context = itemView.context
        cityNameTextView.text = name
        itemView.invalidate()
    }
}