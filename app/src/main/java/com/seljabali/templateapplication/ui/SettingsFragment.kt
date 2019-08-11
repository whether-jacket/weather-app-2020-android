package com.seljabali.templateapplication.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.seljabali.templateapplication.R
import android.view.View
import com.seljabali.core.utilities.Res

class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return
        view.setBackgroundColor(Res.getColorViaAttr(context, android.R.attr.colorBackground))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        (activity as HomeActivity).setToolBarTitle(getString(R.string.settings))
        setPreferencesFromResource(R.xml.sample_settings, rootKey)
        setupViews()
        loadSavedPreferences()
    }

    private fun setupViews() {

    }

    private fun onNightModeSwitched(enabled: Boolean): Boolean {
        return true
    }

    private fun loadSavedPreferences() {
    }
}
