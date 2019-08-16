package com.seljabali.widgets.landing

import android.os.Bundle
import android.util.Log
import android.view.*
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.seljabali.core.BaseFragment
import com.seljabali.core.utilities.Keyboard
import com.seljabali.core.utilities.observeOnMain
import com.seljabali.widgets.*
import kotlinx.android.synthetic.main.fragment_widgets_landing.*
import java.lang.ref.WeakReference

class WidgetsLandingFragment : BaseFragment(), WidgetRecyclerViewAdapter.WidgetClickListener, MenuItem.OnActionExpandListener {

    companion object {
        @JvmStatic
        fun newInstance(): WidgetsLandingFragment = WidgetsLandingFragment()
        @JvmStatic
        val TAG: String = WidgetsLandingFragment::class.java.simpleName
        private const val TYPE_TIME_SECONDS = 1L
    }

    private lateinit var searchMenuItem: MenuItem
    private lateinit var adapter: WidgetRecyclerViewAdapter
    private var widgetsLandingFragmentViewer: WeakReference<WidgetsLandingFragmentViewer>? = null

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
            Widgets.Badge -> TODO()
            Widgets.BiometricPrompt -> TODO()
            Widgets.BottomAppBar -> TODO()
            Widgets.BottomNavigation -> TODO()
            Widgets.BottomSheet -> TODO()
            Widgets.Button -> startFragment(ButtonFragment.newInstance(), ButtonFragment.TAG)
            Widgets.CalendarView -> TODO()
            Widgets.CardView -> TODO()
            Widgets.CheckBox -> startFragment(CheckBoxFragment.newInstance(), CheckBoxFragment.TAG)
            Widgets.CheckedTextView -> TODO()
            Widgets.Chips -> TODO()
            Widgets.Chronometer -> startFragment(ChronometerFragment.newInstance(), ChronometerFragment.TAG)
            Widgets.CompoundButton -> TODO()
            Widgets.DatePicker -> startFragment(DatePickerFragment.newInstance(), DatePickerFragment.TAG)
            Widgets.Dialog -> TODO()
            Widgets.DialogFragment -> TODO()
            Widgets.DrawerLayout -> TODO()
            Widgets.EdgeEffect -> TODO()
            Widgets.EditText -> TODO()
            Widgets.ExtendedFloatingActionButton -> TODO()
            Widgets.FloatingActionButton -> TODO()
            Widgets.GridLayout -> TODO()
            Widgets.GridView -> TODO()
            Widgets.ImageButton -> TODO()
            Widgets.ImageSwitcher -> TODO()
            Widgets.ImageView -> TODO()
            Widgets.Magnifier -> TODO()
            Widgets.MaterialButton -> TODO()
            Widgets.MaterialButtonToggleGroup -> TODO()
            Widgets.MaterialCardView -> TODO()
            Widgets.MaterialTextView -> TODO()
            Widgets.MediaController -> TODO()
            Widgets.Menu -> TODO()
            Widgets.MultiAutoCompleteTextView -> TODO()
            Widgets.NumberPicker -> TODO()
            Widgets.ProgressBar -> startFragment(ProgressBarFragment.newInstance(), ProgressBarFragment.TAG)
            Widgets.RadioButton -> startFragment(RadioButtonFragment.newInstance(), RadioButtonFragment.TAG)
            Widgets.RatingBar -> TODO()
            Widgets.RecyclerView -> TODO()
            Widgets.SnackBar -> TODO()
            Widgets.SeekBar -> startFragment(SeekBarFragment.newInstance(), SeekBarFragment.TAG)
            Widgets.Spinner -> TODO()
            Widgets.StatusBar -> startFragment(StatusFragment.newInstance(), StatusFragment.TAG)
            Widgets.Switch -> TODO()
            Widgets.TabLayout -> TODO()
            Widgets.TextClock -> startFragment(TextClock.newInstance(), TextClock.TAG)
            Widgets.TextView -> TODO()
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
        Keyboard.hide(context, view!!)
        return true
    }

    fun setWidgetsLandingFragmentViewer(viewer: WidgetsLandingFragmentViewer) {
        widgetsLandingFragmentViewer = WeakReference(viewer)
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

    private fun startFragment(fragment: BaseFragment, tag: String) {
        searchMenuItem.collapseActionView()
        searchMenuItem.isVisible = false
        widgetsLandingFragmentViewer?.get()?.showFragment(fragment, tag)
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

    interface WidgetsLandingFragmentViewer {
        fun showFragment(baseFragment: BaseFragment, tag: String)
    }

}