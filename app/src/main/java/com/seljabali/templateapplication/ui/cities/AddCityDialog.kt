package com.seljabali.templateapplication.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.seljabali.core.activityfragment.BaseDialogFragment
import com.seljabali.templateapplication.R

class AddCityDialog : BaseDialogFragment() {

    companion object {
        val TAG: String = AddCityDialog::class.java.simpleName
        fun newInstance() = AddCityDialog()
    }

    private lateinit var cityNameTextInputEditText: TextInputEditText
    private lateinit var addCityButton: Button
    lateinit var addCityListener: AddCityListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_dialog_add_city, container, false)
        cityNameTextInputEditText = view.findViewById(R.id.city_name_text_input_edit_text)
        addCityButton = view.findViewById(R.id.add_city_button)
        addCityButton.setOnClickListener { onCityAddClick() }
        return view
    }

    override fun isShowingTitle(): Boolean = false

    override fun isCancelableDialog(): Boolean = true

    override fun getTitle(): String = getString(R.string.add_city)

    private fun onCityAddClick() {
        val enteredCityName = cityNameTextInputEditText.text.toString()
        if (enteredCityName.isBlank()) {
            cityNameTextInputEditText.error = getString(R.string.city_name_cannot_be_blank)
            return
        }
        cityNameTextInputEditText.error = ""
        addCityListener.onCityAdded(enteredCityName)
        dismiss()
    }
}

interface AddCityListener {
    fun onCityAdded(cityName: String)
}