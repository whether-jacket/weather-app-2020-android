package com.seljabali.widgets.landing

import com.seljabali.widgets.R

enum class Widgets (val title:String, val drawable: Int) {
    AnalogClock("Analog Clock", R.drawable.analog_clock),
    AutoCompleteTextView("AutoComplete TextView", R.drawable.autocomplete_textview),
    Button("Button", R.color.transparent),
    CalendarView("Calendar", R.color.transparent),
    CheckBox("CheckBox", R.drawable.checkbox),
    CheckedTextView("CheckedTextView", R.color.transparent),
    Chronometer("Chronometer", R.color.transparent),
    CompoundButton("CompoundButton", R.color.transparent),
    DatePicker("DatePicker", R.drawable.date_picker),
    EdgeEffect("EdgeEffect", R.color.transparent),
    EditText("EditText", R.color.transparent),
    GridLayout("GridLayout", R.color.transparent),
    GridView("GridView", R.color.transparent),
    ImageButton("ImageButton", R.color.transparent),
    ImageSwitcher("ImageSwitcher", R.color.transparent),
    ImageView("ImageView", R.color.transparent),
    Magnifier("Magnifier", R.color.transparent),
    MediaController("MediaController", R.color.transparent),
    MultiAutoCompleteTextView("MultiAutoCompleteTextView", R.color.transparent),
    NumberPicker("NumberPicker", R.color.transparent),
    ProgressBar("Progress Bar", R.drawable.progress_bar),
    RadioButton("Radio Button", R.drawable.radio_buttons),
    RatingBar("RatingBar", R.color.transparent),
    SeekBar("Seek Bar", R.drawable.seek_bar),
    StatusBar("Status Bar", R.drawable.status_bar),
    TextClock("Text Clock", R.drawable.text_clock),
    TimePicker("Time Picker", R.drawable.time_picker),
    Toast("Toast", R.drawable.toast),
    ToolBar("Tool Bar", R.drawable.tool_bar),
}