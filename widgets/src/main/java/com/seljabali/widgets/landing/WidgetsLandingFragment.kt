package com.seljabali.widgets.landing

import android.os.Bundle
import android.util.Log
import android.view.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.seljabali.core.BaseFragment
import com.seljabali.core.utilities.Keyboard
import com.seljabali.widgets.*
import kotlinx.android.synthetic.main.fragment_widgets_landing.*
import java.lang.ref.WeakReference

class WidgetsLandingFragment : BaseFragment(), WidgetRecyclerViewAdapter.WidgetClickListener, MenuItem.OnActionExpandListener {

    companion object {
        @JvmStatic
        fun newInstance(): WidgetsLandingFragment = WidgetsLandingFragment()
        val TAG: String = WidgetsLandingFragment::class.java.simpleName
        const val TYPE_TIME_SECONDS = 1L
    }

    private lateinit var searchMenuItem: MenuItem
    private lateinit var adapter: WidgetRecyclerViewAdapter
    private var searchDisposable: Disposable? = null
    private var widgetsLandingFragmentViewer: WeakReference<WidgetsLandingFragmentViewer>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_widgets_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWidgetList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        val searchView = SearchView(context)
        with(menu.add(Menu.NONE, Menu.NONE, Menu.NONE, R.string.search)) {
            setIcon(android.R.drawable.ic_menu_search)
            setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
            this.setOnActionExpandListener(this@WidgetsLandingFragment)
            searchMenuItem = this
        }
        searchDisposable = searchView.queryTextChanges()
                .debounce(TYPE_TIME_SECONDS, TimeUnit.SECONDS)
                .mergeWith(Observable.empty())
                .map { enteredSearch -> widgetSearch(enteredSearch) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ filteredWidgets -> onSearch(filteredWidgets) },
                        { error: Throwable? -> Log.e(tag, error.toString()) })
    }

    override fun onStop() {
        super.onStop()
        if (searchDisposable?.isDisposed == true) {
            searchDisposable!!.dispose()
            searchDisposable = null
        }
    }

    override fun onWidgetClick(widgetClicked: Widgets) {
        when (widgetClicked) {
            Widgets.Button -> startFragment(ButtonFragment.newInstance(), ButtonFragment.getTag())
            Widgets.StatusBar -> startFragment(StatusFragment.newInstance(), StatusFragment.getTag())
            Widgets.ToolBar -> startFragment(ToolbarFragment.newInstance(), ToolbarFragment.getTag())
            Widgets.ProgressBar -> startFragment(ProgressBarFragment.newInstance(), ProgressBarFragment.getTag())
            Widgets.AbsSeekBar -> startFragment(AbsSeekBarFragment.newInstance(), AbsSeekBarFragment.getTag())
            Widgets.AnalogClock -> startFragment(AnalogClock.newInstance(), AnalogClock.getTag())
            Widgets.TextClock -> startFragment(TextClock.newInstance(), TextClock.getTag())
            Widgets.AutoCompleteTextView -> startFragment(AutoCompleteTextViewFragment.newInstance(), AutoCompleteTextViewFragment.getTag())
            Widgets.CalendarView -> TODO()
            Widgets.CheckBox -> TODO()
            Widgets.CheckedTextView -> TODO()
            Widgets.Chronometer -> TODO()
            Widgets.CompoundButton -> TODO()
            Widgets.DatePicker -> TODO()
            Widgets.EdgeEffect -> TODO()
            Widgets.EditText -> TODO()
            Widgets.GridLayout -> TODO()
            Widgets.GridView -> TODO()
            Widgets.ImageButton -> TODO()
            Widgets.ImageSwitcher -> TODO()
            Widgets.ImageView -> TODO()
            Widgets.Magnifier -> TODO()
            Widgets.MediaController -> TODO()
            Widgets.MultiAutoCompleteTextView -> TODO()
            Widgets.NumberPicker -> TODO()
            Widgets.RadioButton -> TODO()
            Widgets.RadioGroup -> TODO()
            Widgets.RatingBar -> TODO()
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