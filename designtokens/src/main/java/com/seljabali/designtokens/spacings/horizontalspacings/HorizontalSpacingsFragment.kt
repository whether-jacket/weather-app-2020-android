package com.seljabali.designtokens.spacings.horizontalspacings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import kotlinx.android.synthetic.main.fragment_horizontal_spacings.*

class HorizontalSpacingsFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = HorizontalSpacingsFragment::class.java.simpleName
        fun newInstance() = HorizontalSpacingsFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.horizontal_spacings)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_horizontal_spacings, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        horizontalSpacingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = HorizontalSpacingsAdapter().apply {
                setVerticalSpacings(HorizontalSpacings.values())
            }
        }
    }

}