package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_toast.*

class ToastFragment : BaseToolbarFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = ToastFragment()
        @JvmStatic
        val TAG: String = ToastFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_toast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortToastButton.setOnClickListener { onShortToastButtonClicked() }
        longToastButton.setOnClickListener { onLongToastButtonClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.toast)

    private fun onShortToastButtonClicked() {
        Toast.makeText(context, R.string.foo, Toast.LENGTH_SHORT).show()
    }

    private fun onLongToastButtonClicked() {
        Toast.makeText(context, R.string.bar, Toast.LENGTH_LONG).show()
    }
}
