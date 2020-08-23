package com.seljabali.designtokens.textappearances.textappearancesselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.designtokens.R
import com.seljabali.designtokens.DesignTokensActivity
import com.seljabali.designtokens.LandingItem
import com.seljabali.designtokens.textappearances.appcompat.AppCompatTextAppearancesFragment
import com.seljabali.designtokens.textappearances.material.MaterialTextAppearancesFragment
import kotlinx.android.synthetic.main.fragment_text_size_types_landing_page.*

class TextSizeTypeFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = TextSizeTypeFragment::class.java.simpleName
        fun newInstance() = TextSizeTypeFragment()
    }

    lateinit var adapter: TextSizeTypeAdapter

    override fun getToolbarTitle(): String = getString(R.string.text_appearances)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text_size_types_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TextSizeTypeAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(TextSizeTypeLandingItem.values() as Array<LandingItem>)
        textSizeTypesGridView.adapter = adapter
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
            TextSizeTypeLandingItem.APP_COMPAT -> homeActivity.showFragment(
                AppCompatTextAppearancesFragment.newInstance(), AppCompatTextAppearancesFragment.TAG)
            TextSizeTypeLandingItem.MATERIAL -> homeActivity.showFragment(
                MaterialTextAppearancesFragment.newInstance(), MaterialTextAppearancesFragment.TAG)
//            TextSizeTypeLandingItem.CUSTOM -> { }
        }
        return
    }

}