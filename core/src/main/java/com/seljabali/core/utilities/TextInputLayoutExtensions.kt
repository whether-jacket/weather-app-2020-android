package com.seljabali.core.utilities

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hasError(): Boolean = !this.error.isNullOrEmpty()

fun TextInputLayout.hasNoError(): Boolean = !this.hasError()

fun TextInputLayout.clearError() { error = "" }