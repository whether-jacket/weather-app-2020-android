package com.seljabali.core.activityfragment.backpresslistener

/*
 * This is to allow fragments to listen and possibly stop the user from leaving the fragment.
 * return false means allow for fragment to get popped off the stack
 * return true means stop the fragment from getting popped off the stack
 */
interface OnBackPressListener {
    fun shouldStopBackPress(): Boolean
}