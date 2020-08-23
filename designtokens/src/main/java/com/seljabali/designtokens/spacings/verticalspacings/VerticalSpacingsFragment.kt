package com.seljabali.designtokens.spacings.verticalspacings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import kotlinx.android.synthetic.main.fragment_vertical_spacings.*

class VerticalSpacingsFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = VerticalSpacingsFragment::class.java.simpleName
        fun newInstance() = VerticalSpacingsFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.vertical_spacings)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_vertical_spacings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verticalSpacingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
            adapter = VerticalSpacingsAdapter().apply {
                setVerticalSpacings(VerticalSpacings.values())
            }
        }
    }

}