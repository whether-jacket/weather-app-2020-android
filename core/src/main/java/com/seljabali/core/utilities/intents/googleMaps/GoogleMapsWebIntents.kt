@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents.googleMaps

import android.content.Intent
import android.net.Uri
import com.seljabali.core.utilities.intents.Intents

// Api Documentation from:
// https://developers.google.com/maps/documentation/urls/guide

private const val querySearchMapsApi1 = "https://www.google.com/maps/search/?api=1"
private const val locationInMapApi1 = "https://www.google.com/maps/@?api=1"

fun Intents.Companion.getQueryMapInWeb(query: String): Intent = getQueryMapInWeb(query, null)

fun Intents.Companion.getQueryMapInWeb(query: String, locationId: String?): Intent {
    val builder = StringBuilder(querySearchMapsApi1)
    builder.append("&query=" + Uri.encode(query))
    if (locationId != null) {
        builder.append("&query_place_id=$locationId")
    }
    return Intent(Intent.ACTION_VIEW, Uri.parse(builder.toString()))
}

fun Intents.Companion.getMapInWeb(latitude: Float, longitude: Float): Intent = getMapInWeb(latitude, longitude, null, null)

fun Intents.Companion.getMapInWeb(latitude: Float, longitude: Float, type: MapViewType?): Intent = getMapInWeb(latitude, longitude, type)

fun Intents.Companion.getMapInWeb(latitude: Float, longitude: Float, zoom: Int?): Intent = getMapInWeb(latitude, longitude, zoom, null)

fun Intents.Companion.getMapInWeb(latitude: Float, longitude: Float, zoom: Int?, type: MapViewType?): Intent {
    val builder = StringBuilder(locationInMapApi1).apply {
        append("&map_action=map")
        append("&center=$latitude,$longitude")
    }
    if (zoom != null) {
        builder.append("&zoom=$zoom")
    }
    if (type != null) {
        builder.append("&basemap=${type.key}")
    }
    return Intent(Intent.ACTION_VIEW, Uri.parse(builder.toString()))
}
