package com.seljabali.templateapplication.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemDragListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.templateapplication.R
import kotlinx.android.synthetic.main.fragment_cities.*
import setTheme

class CitiesFragment : BaseFragment(), AddCityDialogListener {

    companion object {
        val TAG: String = CitiesFragment::class.java.simpleName
        fun newInstance() = CitiesFragment()
    }

    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cities, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_city_button.setOnClickListener { showAddCityDialog() }
        val dataSet = listOf("Tempe", "San Francisco", "New York")
        cityAdapter = CityAdapter(dataSet)
        with(cities_drag_drop_swipe_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
            orientation = DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener
            dragListener = onItemDragListener
        }
    }

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
        Toast.makeText(requireContext(), "$cityName was added", Toast.LENGTH_SHORT).setTheme().show()
    }

    private val onItemSwipeListener = object : OnItemSwipeListener<String> {
        override fun onItemSwiped(position: Int, direction: OnItemSwipeListener.SwipeDirection, item: String): Boolean {
            Toast.makeText(requireContext(), "Item: $item, position: $position", Toast.LENGTH_SHORT).setTheme().show()
            return false
        }
    }

    private val onItemDragListener = object : OnItemDragListener<String> {
        override fun onItemDragged(previousPosition: Int, newPosition: Int, item: String) {
            Toast.makeText(requireContext(), "From: $previousPosition -> $newPosition", Toast.LENGTH_SHORT).setTheme().show()
        }

        override fun onItemDropped(initialPosition: Int, finalPosition: Int, item: String) {
            // Handle action of item dropped
        }
    }
}
