package com.seljabali.widgets.landing

import android.os.Bundle
import android.util.Log
import android.view.*
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.core.utilities.Keyboard
import com.seljabali.core.utilities.observeOnMain
import com.seljabali.widgets.*
import kotlinx.android.synthetic.main.fragment_widgets_landing.*
import java.lang.ref.WeakReference

class WidgetsLandingFragment : BaseToolbarFragment(), WidgetRecyclerViewAdapter.WidgetClickListener, MenuItem.OnActionExpandListener {

    companion object {
        @JvmStatic
        fun newInstance(): WidgetsLandingFragment = WidgetsLandingFragment()
        @JvmStatic
        val TAG: String = WidgetsLandingFragment::class.java.simpleName
        private const val TYPE_TIME_SECONDS = 1L
    }

    private lateinit var searchMenuItem: MenuItem
    private lateinit var adapter: WidgetRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_widgets_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWidgetList()
    }

    override fun getToolbarTitle(): String = getString(R.string.widgets)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        val searchView = SearchView(context)
        with(menu.add(Menu.NONE, Menu.NONE, Menu.NONE, R.string.search)) {
            searchMenuItem = this
            setIcon(android.R.drawable.ic_menu_search)
            setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
            setOnActionExpandListener(this@WidgetsLandingFragment)
        }
        subscribe(searchView.queryTextChanges()
                .debounce(TYPE_TIME_SECONDS, TimeUnit.SECONDS)
                .mergeWith(Observable.empty())
                .map { enteredSearch -> widgetSearch(enteredSearch) }
                .observeOnMain()
                .subscribe({ filteredWidgets -> onSearch(filteredWidgets) },
                        { error: Throwable? -> Log.e(tag, error.toString()) }))
    }

    override fun onWidgetClick(widgetClicked: Widgets) {
        when (widgetClicked) {
            Widgets.AnalogClock -> startFragment(AnalogClock.newInstance(), AnalogClock.TAG)
            Widgets.AutoCompleteTextView -> startFragment(AutoCompleteTextViewFragment.newInstance(), AutoCompleteTextViewFragment.TAG)
            Widgets.Badge -> { }
            Widgets.BiometricPrompt -> startFragment(BiometricPromptFragment.newInstance(), BiometricPromptFragment.TAG)
            Widgets.BottomAppBar -> { }
            Widgets.BottomNavigation -> { }
            Widgets.BottomSheet -> { }
            Widgets.Button -> startFragment(ButtonFragment.newInstance(), ButtonFragment.TAG)
            Widgets.CalendarView -> { }
            Widgets.CardView -> { }
            Widgets.CheckBox -> startFragment(CheckBoxFragment.newInstance(), CheckBoxFragment.TAG)
            Widgets.CheckedTextView -> { }
            Widgets.Chips -> { }
            Widgets.Chronometer -> startFragment(ChronometerFragment.newInstance(), ChronometerFragment.TAG)
            Widgets.CompoundButton -> { }
            Widgets.DatePicker -> startFragment(DatePickerFragment.newInstance(), DatePickerFragment.TAG)
            Widgets.Dialog -> { }
            Widgets.DialogFragment -> { }
            Widgets.DrawerLayout -> { }
            Widgets.EdgeEffect -> { }
            Widgets.EditText -> { }
            Widgets.ExtendedFloatingActionButton -> { }
            Widgets.FloatingActionButton -> { }
            Widgets.GridLayout -> { }
            Widgets.GridView -> { }
            Widgets.ImageButton -> { }
            Widgets.ImageSwitcher -> { }
            Widgets.ImageView -> { }
            Widgets.Magnifier -> { }
            Widgets.MaterialButton -> { }
            Widgets.MaterialButtonToggleGroup -> { }
            Widgets.MaterialCardView -> { }
            Widgets.MaterialTextView -> { }
            Widgets.MediaController -> { }
            Widgets.Menu -> { }
            Widgets.MultiAutoCompleteTextView -> { }
            Widgets.NumberPicker -> { }
            Widgets.ProgressBar -> startFragment(ProgressBarFragment.newInstance(), ProgressBarFragment.TAG)
            Widgets.RadioButton -> startFragment(RadioButtonFragment.newInstance(), RadioButtonFragment.TAG)
            Widgets.RatingBar -> { }
            Widgets.RecyclerView -> { }
            Widgets.SnackBar -> { }
            Widgets.SeekBar -> startFragment(SeekBarFragment.newInstance(), SeekBarFragment.TAG)
            Widgets.Spinner -> { }
            Widgets.StatusBar -> startFragment(StatusFragment.newInstance(), StatusFragment.TAG)
            Widgets.Switch -> { }
            Widgets.TabLayout -> { }
            Widgets.TextClock -> startFragment(TextClock.newInstance(), TextClock.TAG)
            Widgets.TextView -> { }
            Widgets.TimePicker -> startFragment(TimePickerFragment.newInstance(), TimePickerFragment.TAG)
            Widgets.Toast -> startFragment(ToastFragment.newInstance(), ToastFragment.TAG)
            Widgets.ToolBar -> startFragment(ToolbarFragment.newInstance(), ToolbarFragment.TAG)
        }
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        adapter.clearFilters()
        view?.let { Keyboard.hide(requireContext(), it) }
        return true
    }

    private fun widgetSearch(searchTerm: CharSequence): ArrayList<Widgets> {
        if (searchTerm.isBlank()) {
            return ArrayList()
        }
        val filteredWidgets: ArrayList<Widgets> = ArrayList()
        for (widget in Widgets.values()) {
            if (isKeywordInText(searchTerm.toString(), widget.title)) {
                filteredWidgets.add(widget)
            }
        }
        return filteredWidgets
    }

    private fun onSearch(filteredWidgets: ArrayList<Widgets>) {
        adapter.setFilteredWidgets(filteredWidgets)
    }

    private fun setupWidgetList() {
        rv_widgets.setHasFixedSize(true)
        rv_widgets.layoutManager = LinearLayoutManager(context)
        adapter = WidgetRecyclerViewAdapter(this)
        rv_widgets.adapter = adapter
    }

    private fun startFragment(fragment: BaseToolbarFragment, tag: String) {
        searchMenuItem.collapseActionView()
        searchMenuItem.isVisible = false
        (baseActivity as WidgetsActivity).showFragment(fragment, tag)
    }

    private fun isKeywordInText(keyWord: String, text: String) : Boolean {
        if (keyWord.isBlank() or text.isBlank()) return false
        val key = keyWord.toLowerCase()
        val textArray = text.split(" ")
        for (word in textArray) {
            if (word.toLowerCase().startsWith(key)) return true
        }
        return false
    }

}