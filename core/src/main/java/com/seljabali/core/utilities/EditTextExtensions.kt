package com.seljabali.core.utilities

import android.text.InputFilter
import android.widget.EditText

fun EditText.setFilter(filter: InputFilter) {
    val curFilters = filters
    if (curFilters != null) {
        val newFilters = arrayOfNulls<InputFilter>(curFilters.size + 1)
        System.arraycopy(curFilters, 0, newFilters, 0, curFilters.size)
        newFilters[curFilters.size] = filter
        filters = newFilters
        return
    }
    filters = arrayOf(filter)
}