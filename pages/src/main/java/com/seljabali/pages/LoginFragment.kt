package com.seljabali.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.core.utilities.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTextInputEditText.setText("foo@gmail.com")
        passwordTextInputEditText.setText("12345")
        loginButton.setOnClickListener { onLoginButtonClick() }
    }

    override fun getToolbarTitle(): String = getString(R.string.login)

    override fun onDestroyView() {
        super.onDestroyView()
        Keyboard.hide(context, usernameTextInputEditText)
        Keyboard.hide(context, passwordTextInputEditText)
    }

    private fun onLoginButtonClick() {
        var validLogin = true
        validLogin = validLogin and isUserNameValid()
        validLogin = validLogin and isPasswordValid()
        if (validLogin) {
        }
    }

    private fun isUserNameValid(): Boolean {
        if (usernameTextInputEditText.isBlank()) {
            usernameTextInputLayout.error = getString(R.string.username_cannot_be_blank)
            return false
        }
        if (usernameTextInputEditText.getTextValue().isEmailInvalid()) {
            usernameTextInputLayout.error = getString(R.string.invalid_email_address)
            return false
        }
        usernameTextInputLayout.clearError()
        return true
    }

    private fun isPasswordValid(): Boolean {
        if (passwordTextInputEditText.isBlank()) {
            passwordTextInputLayout.error = getString(R.string.password_cannot_be_blank)
            return false
        }
        passwordTextInputLayout.clearError()
        return true
    }
}