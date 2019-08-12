package com.seljabali.core.utilities

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.ImageView

import java.net.URISyntaxException

// taken from https://stackoverflow.com/questions/13209494/how-to-get-the-full-file-path-from-uri#13209522
@SuppressLint("NewApi", "ObsoleteSdkInt")
@Throws(URISyntaxException::class)
fun Uri.getPath(context: Context): String? {
    var uri = this
    val needToCheckUri = Build.VERSION.SDK_INT >= 19
    var selection: String? = null
    var selectionArgs: Array<String>? = null
    // Uri is different in versions after KitKat (Android 4.4)
    if (needToCheckUri && DocumentsContract.isDocumentUri(context.applicationContext, uri)) {
        when {
            uri.isExternalStorageDocument() -> {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            }
            uri.isDownloadsDocument() -> {
                val id = DocumentsContract.getDocumentId(uri)
                uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
            }
            uri.isMediaDocument() -> {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                when (type) {
                    "image" -> uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(split[1])
            }
        }
    }
    if ("content".equals(uri.scheme, ignoreCase = true)) {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                val path = cursor.getString(columnIndex)
                cursor.close()
                return path
            }
        } catch (e: Exception) {
            cursor?.close()
        }

    } else if ("file".equals(uri.scheme, ignoreCase = true)) {
        return uri.path
    }
    return null
}

fun Uri.isExternalStorageDocument(): Boolean = "com.android.externalstorage.documents" == authority

fun Uri.isDownloadsDocument(): Boolean = "com.android.providers.downloads.documents" == authority

fun Uri.isMediaDocument(): Boolean = "com.android.providers.media.documents" == authority

object UriUtil {

    @JvmStatic
    fun getUriFromImageView(context: Context, imageView: ImageView): Uri {
        val drawable = imageView.drawable
        return getUriFromBitmapDrawable(context, drawable as BitmapDrawable)
    }

    @JvmStatic
    private fun getUriFromBitmapDrawable(context: Context, drawable: BitmapDrawable): Uri {
        val bitmap = drawable.bitmap
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "", null)
        return Uri.parse(path)
    }
}