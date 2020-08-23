package com.seljabali.core.activityfragment.backpresslistener

interface BackPressButtonHolder {
    fun addBackPressListener(onBackPressListener: OnBackPressListener)
    fun removeBackPressListener(onBackPressListener: OnBackPressListener)
}