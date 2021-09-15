package com.seljabali.templateapplication.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.designtokens.DesignTokensActivity
import com.seljabali.pages.PagesActivity
import com.seljabali.templateapplication.ForegroundService
import com.seljabali.theming.ThemingActivity
import com.seljabali.templateapplication.R
import com.seljabali.widgets.WidgetsActivity
import io.objectbox.Box
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class SettingsFragment : BaseFragment() {

    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance() = SettingsFragment()
    }

    private val userPreferencesBox: Box<UserPreferencesDb> by inject(named(DB_USER_PREFERENCES_BOX))
    private val userPreference: UserPreferencesDb get() = userPreferencesBox.all[0]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_settings, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateViewsFromDb()
        setupViewClickListeners()
    }

    private fun populateViewsFromDb() {
        setupUserPreferencesDb()
        val currentUserPreference = userPreference
        temperature_in_metric_switch.isChecked = currentUserPreference.isTemperatureInMetric
        dark_theme_switch.isChecked = currentUserPreference.themeId == R.style.Theme_Illini
        speed_in_metric_switch.isChecked = currentUserPreference.isSpeedInMetric
        theming_button.isChecked = currentUserPreference.isTemperatureInMetric
    }

    private fun setupViewClickListeners() {
        temperature_in_metric_switch.setOnCheckedChangeListener { _, isChecked ->
            userPreferencesBox.put(userPreference.apply { isTemperatureInMetric = isChecked })
        }
        dark_theme_switch.setOnCheckedChangeListener { _, isChecked ->
            val newTheme = if (isChecked) R.style.Theme_Illini else R.style.Theme_Tokyo
            userPreferencesBox.put(userPreference.apply { themeId = newTheme })
            val homeActivity = activity ?: return@setOnCheckedChangeListener
            homeActivity.setTheme(newTheme)
            homeActivity.supportFragmentManager.popBackStack(0,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            homeActivity.recreate()
        }
        speed_in_metric_switch.setOnCheckedChangeListener { _, isChecked ->
            userPreferencesBox.put(userPreference.apply { isSpeedInMetric = isChecked })
        }
        theming_button.setOnClickListener {
            val intentStop = Intent(requireContext(), ForegroundService::class.java)
            requireContext().startService(intentStop)
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

    private fun setupUserPreferencesDb() {
        if (userPreferencesBox.all.isNotEmpty()) return
        userPreferencesBox.put(UserPreferencesDb())
    }
}
