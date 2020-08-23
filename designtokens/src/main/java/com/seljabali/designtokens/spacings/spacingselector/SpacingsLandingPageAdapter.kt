package com.seljabali.designtokens.spacings.spacingselector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem
import io.reactivex.subjects.PublishSubject

class SpacingsLandingPageAdapter(private val clickListener: (LandingItem) -> Unit) : BaseAdapter() {

    companion object {
        const val THROTTLE_FIRST_S: Long = 1000
    }

    private var itemViewClickSubject = PublishSubject.create<Unit>()
    private var landingPageItems: Array<LandingItem> = emptyArray()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context = parent!!.context
        val landingPageItemAtPosition = getItem(position)
        with(
            SpacingsLandingPageViewHolder(
                convertView ?: LayoutInflater.from(context).inflate(
                    R.layout.item_page_item, parent, false
                )
            )
        ) {
            bind(landingPageItemAtPosition)
            itemView.clicks()
                .throttleFirst(THROTTLE_FIRST_S, java.util.concurrent.TimeUnit.MILLISECONDS)
                .map { clickListener.invoke(landingPageItemAtPosition) }
                .subscribe(itemViewClickSubject)
            return itemView
        }
    }

    override fun getItem(position: Int): LandingItem = landingPageItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = landingPageItems.size

    fun setLandingPageItems(landingPageItems: Array<LandingItem>) {
        this.landingPageItems = landingPageItems
        notifyDataSetChanged()
    }

    fun clearSubscriptions() {
        itemViewClickSubject.onComplete()
    }

    fun renewSubscriptions() {
        itemViewClickSubject = PublishSubject.create<Unit>()
    }
}