package com.seljabali.templateapplication.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemDragListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.database.DB_LOCATION_BOX
import com.seljabali.database.models.LocationDb
import com.seljabali.templateapplication.R
import io.objectbox.Box
import kotlinx.android.synthetic.main.fragment_cities.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class CitiesFragment : BaseFragment(), AddCityDialogListener {

    companion object {
        val TAG: String = CitiesFragment::class.java.simpleName
        fun newInstance() = CitiesFragment()
    }

    private val locationBox: Box<LocationDb> by inject(named(DB_LOCATION_BOX))
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cities, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_city_button.setOnClickListener { showAddCityDialog() }
        setupLocationDb()
        cityAdapter = CityAdapter(getAllLocationsFromDb())
        with(cities_drag_drop_swipe_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener
            dragListener = onItemDragListener
        }
    }

    private fun setupLocationDb() {
        val locationsSaved = locationBox.all
        if (locationsSaved.isNotEmpty()) return
        locationBox.put(LocationDb(cityName = "San Francisco", regionName = "CA", position = 0))
        locationBox.put(LocationDb(cityName = "Tempe", regionName = "AZ", position = 1))
        locationBox.put(LocationDb(cityName = "New York", regionName = "NY", position = 2))
    }

    private fun getAllLocationsFromDb(): List<LocationDb> = locationBox.all.sortedBy { it.position }

    private fun showAddCityDialog() {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val prevDialog = parentFragmentManager.findFragmentByTag(AddCityDialog.TAG)
        prevDialog?.let { fragmentTransaction.remove(it) }
        fragmentTransaction.addToBackStack(null)

        val addCityDialog = AddCityDialog.newInstance()
        addCityDialog.addCityDialogListener = this
        addCityDialog.show(fragmentTransaction, AddCityDialog.TAG)
    }

    override fun onCityAdded(cityName: String) {
        val locationCount = getAllLocationsFromDb().count()
        locationBox.put(
            LocationDb(
                cityName = cityName,
                regionName = "",
                woeId = 0,
                position = locationCount - 1
            )
        )
        updateAdapterFromDb()
    }

    private fun updateAdapterFromDb() {
        cityAdapter.dataSet = getAllLocationsFromDb()
    }

    private fun getLocationByPosition(position: Int): LocationDb? =
        locationBox.all.firstOrNull { it.position == position }

    private val onItemSwipeListener = object : OnItemSwipeListener<LocationDb> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: LocationDb
        ): Boolean {
            locationBox.remove(item.id)
            return false
        }
    }

    private val onItemDragListener = object : OnItemDragListener<LocationDb> {
        override fun onItemDragged(previousPosition: Int, newPosition: Int, item: LocationDb) {
            val fromA =
                getLocationByPosition(previousPosition)?.apply { position = newPosition } ?: return
            val toB =
                getLocationByPosition(newPosition)?.apply { position = previousPosition } ?: return
            locationBox.put(fromA)
            locationBox.put(toB)
            updateAdapterFromDb()
        }

        override fun onItemDropped(initialPosition: Int, finalPosition: Int, item: LocationDb) {
            // Do nothing
        }
    }
}
