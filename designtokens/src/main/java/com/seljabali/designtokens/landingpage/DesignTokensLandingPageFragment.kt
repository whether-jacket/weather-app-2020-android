package com.seljabali.designtokens.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import com.seljabali.designtokens.DesignTokensActivity
import com.seljabali.designtokens.LandingItem
import com.seljabali.designtokens.colors.ColorsFragment
import com.seljabali.designtokens.cornerradiuses.CornerRadiusFragment
import com.seljabali.designtokens.letterspacings.LetterSpacingsFragment
import com.seljabali.designtokens.spacings.showcasing.SpacingsShowCasingFragment
import com.seljabali.designtokens.textappearances.textappearancesselector.TextSizeTypeFragment
import com.seljabali.designtokens.transparency.TransparencyFragment
import kotlinx.android.synthetic.main.fragment_design_tokens_landing_page.*

class DesignTokensLandingPageFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = DesignTokensLandingPageFragment::class.java.simpleName
        fun newInstance() = DesignTokensLandingPageFragment()
    }

    lateinit var adapter: LandingPageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_design_tokens_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(LandingPageItems.values() as Array<LandingItem>)
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

    override fun getToolbarTitle(): String = getString(R.string.design_tokens)

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val homeActivity = baseActivity as DesignTokensActivity
        when (landingPageItem) {
            LandingPageItems.SPACING -> homeActivity.showFragment(SpacingsShowCasingFragment.newInstance(), SpacingsShowCasingFragment.TAG)
            LandingPageItems.TEXT_SIZES -> homeActivity.showFragment(TextSizeTypeFragment.newInstance(), TextSizeTypeFragment.TAG)
            LandingPageItems.CORNER_RADIUS -> homeActivity.showFragment(CornerRadiusFragment.newInstance(), CornerRadiusFragment.TAG)
//            LandingPageItems.ELEVATIONS -> homeActivity.showFragment(ElevationsFragment.newInstance(), ElevationsFragment.TAG)
            LandingPageItems.LETTER_SPACINGS -> homeActivity.showFragment(LetterSpacingsFragment.newInstance(), LetterSpacingsFragment.TAG)
            LandingPageItems.COLORS -> homeActivity.showFragment(ColorsFragment.newInstance(), ColorsFragment.TAG)
            LandingPageItems.TRANSPARENCIES -> homeActivity.showFragment(TransparencyFragment.newInstance(), TransparencyFragment.TAG)
        }
        return
    }

}