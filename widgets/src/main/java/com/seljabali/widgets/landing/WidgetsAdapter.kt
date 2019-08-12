package com.seljabali.widgets.landing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seljabali.widgets.R

class WidgetRecyclerViewAdapter(private val widgetClickListener: WidgetClickListener) : RecyclerView.Adapter<WidgetItemView>() {

    private var context: Context? = null
    private var widgetList: Array<Widgets> = Widgets.values()
    private var widgetFilteredList: ArrayList<Widgets> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetItemView {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_widget, parent, false)
        context = parent.context
        return WidgetItemView(view)
    }

    override fun onBindViewHolder(holder: WidgetItemView, position: Int) {
        val widgetAtPosition = getItemAtPosition(position)
        holder.tvWidgetName.text = widgetAtPosition.title
        holder.ivWidgetPic.setBackgroundResource(widgetAtPosition.drawable)
        holder.itemView.setOnClickListener { widgetClickListener.onWidgetClick(widgetAtPosition) }
    }

    override fun getItemCount(): Int = if (widgetFilteredList.size == 0) widgetList.size else widgetFilteredList.size

    fun setFilteredWidgets(widgetFilteredList: ArrayList<Widgets>) {
        this.widgetFilteredList = widgetFilteredList
        notifyDataSetChanged()
    }

    fun clearFilters() {
        this.widgetFilteredList = ArrayList()
        notifyDataSetChanged()
    }

    private fun getItemAtPosition(position: Int): Widgets = if (widgetFilteredList.size == 0) widgetList[position] else widgetFilteredList[position]

    interface WidgetClickListener {
        fun onWidgetClick(widgetClicked: Widgets)
    }
}