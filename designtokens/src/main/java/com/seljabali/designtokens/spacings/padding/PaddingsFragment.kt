package com.seljabali.designtokens.spacings.padding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import kotlinx.android.synthetic.main.fragment_paddings.*

class PaddingsFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = PaddingsFragment::class.java.simpleName
        fun newInstance() = PaddingsFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.surrounding_spacings)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_paddings, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paddingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = PaddingsAdapter().apply {
                setPaddings(Paddings.values())
            }
        }
    }

}