package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.core.utilities.PhoneDevice
import kotlinx.android.synthetic.main.fragment_biometric_prompt.*
import java.util.concurrent.Executors

class BiometricPromptFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = BiometricPromptFragment()

        @JvmStatic
        val TAG: String = BiometricPromptFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_biometric_prompt, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBiometricPrompt.setOnClickListener { onBiometricPromptButtonClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.biometric_prompt)

    private fun onBiometricPromptButtonClicked() {
        if (!PhoneDevice.hasFingerPrint(requireContext())) {
            Toast.makeText(context, R.string.fingerprint_hardware_na, Toast.LENGTH_SHORT).show()
            return
        }
        val activity = activity ?: return
        val executor = Executors.newSingleThreadExecutor()
        val myBiometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                activity.runOnUiThread {
                    Toast.makeText(context, R.string.successful_login, Toast.LENGTH_SHORT).show()
                }
            }
        })
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.fingerprint_authentication))
            .setDescription(getString(R.string.fingerprint_authentication_description))
            .setNegativeButtonText(getString(R.string.cancel))
            .build()
        myBiometricPrompt.authenticate(promptInfo)
    }

}
