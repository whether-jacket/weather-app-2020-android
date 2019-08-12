package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_autocomplete_text_view.*
import android.widget.ArrayAdapter
import com.seljabali.core.BaseFragment

class AutoCompleteTextViewFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): AutoCompleteTextViewFragment {
            return AutoCompleteTextViewFragment()
        }

        fun getTag(): String {
            return AutoCompleteTextViewFragment::class.java.simpleName
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_autocomplete_text_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item, arrayListOf("apple", "grape", "banana"))
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 1 // will start working from first character
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.autocomplete_text_view)
        }
    }
}