@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Intent
import android.net.Uri
import java.net.URL

fun Intents.Companion.getOpenWebBrowser(urlParam: String): Intent {
    var url = urlParam
    if (!url.startsWith("https://") && !url.startsWith("http://")) {
        url = "http://$url"
    }
    return Intent(Intent.ACTION_VIEW, Uri.parse(url))
}

fun Intents.Companion.getOpenWebBrowser(url: URL): Intent = getOpenWebBrowser(url.toString())
