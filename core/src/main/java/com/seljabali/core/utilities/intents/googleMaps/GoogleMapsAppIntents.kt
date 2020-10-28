@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents.googleMaps

import android.content.Intent
import android.net.Uri
import com.seljabali.core.utilities.intents.Intents

private const val mapsPackage = "com.google.android.apps.maps"
private const val navigationQuery = "google.navigation:q="
private const val streetViewQuery = "google.streetview:cbll="

fun Intents.Companion.showLocationInMaps(address: String): Intent {
    val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.`package` = mapsPackage
    return mapIntent
}

fun Intents.Companion.showLocationInNavigation(address: String): Intent {
    val builder = StringBuilder()
    builder.append(navigationQuery)
    builder.append(Uri.encode(address))
    return Intent(Intent.ACTION_VIEW, Uri.parse(builder.toString()))
}

fun Intents.Companion.showLocationInNavigation(latitude: Float, longitude: Float): Intent {
    val builder = StringBuilder().apply {
        append(navigationQuery)
        append(latitude)
        append(",")
        append(longitude)
    }
    return Intent(Intent.ACTION_VIEW, Uri.parse(builder.toString()))
}

fun Intents.Companion.showInStreetView(latitude: Float, longitude: Float): Intent =
    showInStreetView(latitude, longitude, null, null, null, null)

fun Intents.Companion.showInStreetView(latitude: Float, longitude: Float, zoom: Float): Intent =
    showInStreetView(latitude, longitude, null, null, zoom, null)

fun Intents.Companion.showInStreetView(latitude: Float, longitude: Float, zoom: Float, mapZoom: Int): Intent =
    showInStreetView(latitude, longitude, null, null, zoom, mapZoom)

/**
 * Opens the Street View application to the given location.
 * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
 *
 * @param latitude Latitude
 * @param longitude Longitude
 * @param yaw Panorama center-of-view in degrees clockwise from North.
 *
 *
 * Note: The two commas after the yaw parameter are required.
 * They are present for backwards-compatibility reasons.
 * @param pitch Panorama center-of-view in degrees from -90 (look straight up) to 90 (look straight down.)
 * @param zoom Panorama zoom. 1.0 = normal zoom, 2.0 = zoomed in 2x, 3.0 = zoomed in 4x, and so on.
 * A zoom of 1.0 is 90 degree horizontal FOV for a nominal landscape mode 4 x 3 aspect ratio display Android
 * phones in portrait mode will adjust the zoom so that the vertical FOV is approximately the same as the
 * landscape vertical FOV. This means that the horizontal FOV of an Android phone in portrait mode is much
 * narrower than in landscape mode. This is done to minimize the fisheye lens effect that would be present
 * if a 90 degree horizontal FOV was used in portrait mode.
 * @param mapZoom The map zoom of the map location associated with this panorama.
 * This value is passed on to the Maps activity when the Street View "Go to Maps" menu item is chosen.
 * It corresponds to the zoomLevel parameter in {@showLocation(float, float, Integer)}
 */
fun Intents.Companion.showInStreetView(latitude: Float, longitude: Float, yaw: Float?, pitch: Int?, zoom: Float?,
                     mapZoom: Int?): Intent {
    val builder = StringBuilder(streetViewQuery).apply {
        append(latitude)
        append(",")
        append(longitude)
    }
    if (yaw != null || pitch != null || zoom != null) {
        val cbpParam = String.format("%s,,%s,%s", yaw ?: "", pitch ?: "", zoom ?: "")
        builder.append("&cbp=1,")
        builder.append(cbpParam)
    }
    if (mapZoom != null) {
        builder.append("&mz=")
        builder.append(mapZoom)
    }
    return Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse(builder.toString())
    }
}