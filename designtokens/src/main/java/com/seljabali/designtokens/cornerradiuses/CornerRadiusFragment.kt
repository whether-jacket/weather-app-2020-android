package com.seljabali.designtokens.cornerradiuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import kotlinx.android.synthetic.main.fragment_corner_radiuses.*

class CornerRadiusFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = CornerRadiusFragment::class.java.simpleName
        fun newInstance() = CornerRadiusFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.corner_radiuses)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_corner_radiuses, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cornerRadiusesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = CornerRadiusAdapter().apply {
                setCornerRadiuses(CornerRadiuses.values())
            }
        }
    }
}