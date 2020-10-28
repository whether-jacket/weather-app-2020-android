@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Context
import android.content.Intent
import android.net.Uri

private const val googlePlayAppUrlForPackages = "market://details?id="
private const val googlePlayWebUrlForPackages = "https://play.google.com/store/apps/details?id"

private const val googlePlayAppUrlForQueries = "market://search?q=%s&c=apps"
private const val googlePlayWebUrlForQueries = "http://play.google.com/store/search?q=%s&c=apps"

private const val amazonAppUrlForPackages = "amzn://apps/android?p="
private const val amazonWebUrlForPackages = "http://www.amazon.com/gp/mas/dl/android?p="

private const val amazonAppUrlForQueries = "amzn://apps/android?s="
private const val amazonWebUrlForQueries = "http://www.amazon.com/gp/mas/dl/android?s="

// Google Play, packages
fun Intents.Companion.getGooglePlayForPackage(context: Context, packageName: String): Intent {
    var intent: Intent? = getGooglePlayNativeForPackage(packageName)
    if (!isIntentAvailable(context, intent)) {
        intent = getGooglePlayWebForPackage(packageName)
    }
    return intent!!
}

fun Intents.Companion.getGooglePlayNativeForPackage(packageName: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googlePlayAppUrlForPackages + packageName))
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

fun Intents.Companion.getGooglePlayWebForPackage(packageName: String): Intent {
    val intent = getOpenWebBrowser(googlePlayWebUrlForPackages + packageName)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

// Google Play, queries
fun Intents.Companion.getGooglePlayForQuery(context: Context, query: String): Intent? {
    var intent: Intent? = getGooglePlayNativeForQuery(query)
    if (!isIntentAvailable(context, intent)) {
        intent = getGooglePlayWebForQuery(query)
    }
    return intent
}

fun Intents.Companion.getGooglePlayNativeForQuery(query: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googlePlayAppUrlForQueries.format(query)))
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

fun Intents.Companion.getGooglePlayWebForQuery(query: String): Intent {
    val intent = getOpenWebBrowser(googlePlayWebUrlForQueries.format(query))
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}


// Amazon, packages
fun Intents.Companion.getAmazonForPackage(context: Context, packageName: String): Intent {
    var intent: Intent? = getAmazonNativeForPackage(packageName)
    if (!isIntentAvailable(context, intent)) {
        intent = getAmazonWebForPackage(packageName)
    }
    return intent!!
}

fun Intents.Companion.getAmazonNativeForPackage(packageName: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(amazonAppUrlForPackages + packageName))
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

fun Intents.Companion.getAmazonWebForPackage(packageName: String): Intent {
    val intent = getOpenWebBrowser(amazonWebUrlForPackages + packageName)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

// Amazon, queries
fun Intents.Companion.getAmazonForQuery(context: Context, packageName: String): Intent {
    var intent: Intent? = getAmazonNativeForQuery(packageName)
    if (!isIntentAvailable(context, intent)) {
        intent = getAmazonWebForQuery(packageName)
    }
    return intent!!
}

fun Intents.Companion.getAmazonNativeForQuery(packageName: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(amazonAppUrlForQueries + packageName))
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}

fun Intents.Companion.getAmazonWebForQuery(packageName: String): Intent {
    val intent = getOpenWebBrowser(amazonWebUrlForQueries + packageName)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    return intent
}
