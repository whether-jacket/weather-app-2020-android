package com.seljabali.designtokens.transparency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import kotlinx.android.synthetic.main.fragment_transparencies.*

class TransparencyFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = TransparencyFragment::class.java.simpleName
        fun newInstance() = TransparencyFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.transparencies)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_transparencies, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transparenciesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = TransparencyAdapter().apply {
                setTransparencies(Transparencies.values())
            }
        }
    }

}