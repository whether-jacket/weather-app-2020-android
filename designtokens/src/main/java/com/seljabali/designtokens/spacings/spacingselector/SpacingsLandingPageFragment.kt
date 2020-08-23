package com.seljabali.designtokens.spacings.spacingselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import com.seljabali.designtokens.DesignTokensActivity
import com.seljabali.designtokens.LandingItem
import com.seljabali.designtokens.spacings.showcasing.SpacingsShowCasingFragment
import kotlinx.android.synthetic.main.fragment_spacings_landing_page.*

class SpacingsLandingPageFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = SpacingsLandingPageFragment::class.java.simpleName
        fun newInstance() = SpacingsLandingPageFragment()
    }

    lateinit var adapter: SpacingsLandingPageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_spacings_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SpacingsLandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(SpacingsLandingItem.values() as Array<LandingItem>)
        landingPageGridView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        adapter.clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        adapter.renewSubscriptions()
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val homeActivity = baseActivity as DesignTokensActivity
        when (landingPageItem) {
            SpacingsLandingItem.LINEAR -> { } //TODO: Persist Selection
            SpacingsLandingItem.NON_LINEAR -> { }
        }
        homeActivity.showFragment(SpacingsShowCasingFragment.newInstance(), SpacingsShowCasingFragment.TAG)
        return
    }

}