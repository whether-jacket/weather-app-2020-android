package com.seljabali.templateapplication.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.designtokens.DesignTokensActivity
import com.seljabali.pages.PagesActivity
import com.seljabali.theming.ThemingActivity
import com.seljabali.templateapplication.R
import com.seljabali.widgets.WidgetsActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance() = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_settings, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unit_for_temperature_switch.setOnCheckedChangeListener { switch, isChecked ->

        }
        dark_theme_switch.setOnCheckedChangeListener { switch, isChecked ->

        }
        unit_for_speed_switch.setOnCheckedChangeListener { switch, isChecked ->

        }
        theming_button.setOnClickListener {
            val startIntent = Intent(requireContext(), ThemingActivity::class.java)
            startActivity(startIntent)
        }
        widgets_button.setOnClickListener {
            val startIntent = Intent(requireContext(), WidgetsActivity::class.java)
            startActivity(startIntent)
        }
        sample_pages_button.setOnClickListener {
            val startIntent = Intent(requireContext(), PagesActivity::class.java)
            startActivity(startIntent)
        }
        design_tokens_button.setOnClickListener {
            val startIntent = Intent(requireContext(), DesignTokensActivity::class.java)
            startActivity(startIntent)
        }
    }
}

