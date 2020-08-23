package com.seljabali.pages

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import android.view.View
import android.widget.Toast
import androidx.preference.SwitchPreference
import com.seljabali.core.activityfragment.toolbar.BaseToolbarActivity
import com.seljabali.core.utilities.Res

class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    private lateinit var someSwitch: SwitchPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return
        view.setBackgroundColor(Res.getColorViaAttr(context, android.R.attr.colorBackground))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        (activity as BaseToolbarActivity).supportActionBar?.title = getString(R.string.settings)
        setPreferencesFromResource(R.xml.sample_settings, rootKey)
        setupViews()
    }

    private fun setupViews() {
        someSwitch = preferenceScreen.findPreference("someKey") ?: return
        someSwitch.setOnPreferenceChangeListener { _, newValue -> onSomeSwitchSwitched(newValue as Boolean) }
    }

    private fun onSomeSwitchSwitched(enabled: Boolean): Boolean {
        Toast.makeText(context, "Switched: $enabled", Toast.LENGTH_SHORT).show()
        return true
    }
}
