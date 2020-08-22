package com.seljabali.templateapplication.ui.home

import androidx.annotation.MenuRes
import com.seljabali.templateapplication.R

enum class HomePageItems(@MenuRes private val menuItemId: Int) {
    HOME(R.id.home_nav_item),
    CITIES(R.id.cities_nav_item),
    SETTINGS(R.id.settings_nav_item);

    companion object {
        fun getMenuIdOf(menuId: Int): HomePageItems? = values().first { it.menuItemId == menuId }
    }
}