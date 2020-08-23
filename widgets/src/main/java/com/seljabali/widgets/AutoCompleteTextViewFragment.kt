package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_autocomplete_text_view.*
import android.widget.ArrayAdapter
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment

class AutoCompleteTextViewFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        val TAG: String = AutoCompleteTextViewFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): AutoCompleteTextViewFragment = AutoCompleteTextViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_autocomplete_text_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item, arrayListOf("apple", "grape", "banana"))
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 1 // will start working from first character
    }

    override fun getToolbarTitle(): String = getString(R.string.autocomplete_text_view)
}