@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Context
import android.content.Intent
import android.net.Uri

private const val youtubePackage = "com.google.android.youtube"
private const val youtubeUrl = "https://www.youtube.com/"

private const val youtubeNativeId = "vnd.youtube:"
private const val youtubeWebId = youtubeUrl + "watch?v="

private const val youtubeWebQuery = youtubeUrl + "results?search_query="

// Youtube, Id
fun Intents.Companion.getPlayYouTubeId(context: Context, videoId: String): Intent =
        if (isIntentAvailable(context, youtubePackage)) getPlayYouTubeIdNative(videoId)
        else getPlayYouTubeIdWeb(videoId)

fun Intents.Companion.getPlayYouTubeIdNative(videoId: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeNativeId + videoId))

fun Intents.Companion.getPlayYouTubeIdWeb(videoId: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeWebId + videoId))

// Youtube, Query
fun Intents.Companion.getPlayYouTubeQuery(context: Context, videoId: String): Intent =
        if (isIntentAvailable(context, youtubePackage)) getPlayYouTubeQueryNative(videoId)
        else getPlayYouTubeQueryWeb(videoId)

fun Intents.Companion.getPlayYouTubeQueryNative(videoQuery: String): Intent =
        Intent(Intent.ACTION_SEARCH).apply {
            setPackage(youtubePackage)
            putExtra("query", videoQuery)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

fun Intents.Companion.getPlayYouTubeQueryWeb(videoQuery: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeWebQuery + videoQuery))