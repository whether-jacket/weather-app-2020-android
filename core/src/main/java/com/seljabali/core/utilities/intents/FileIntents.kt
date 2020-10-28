@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.seljabali.core.utilities.MimeTypes
import java.io.File

// Regular Files
fun Intents.Companion.getViewFile(fileUri: Uri, fileType: String): Intent =
        Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(fileUri, fileType)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

fun Intents.Companion.getPickFile(): Intent =
        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = MimeTypes.ANY.id
        }

fun Intents.Companion.getGalleryForPhotos(): Intent =
        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = MimeTypes.IMAGE_ANY.id
        }

fun Intents.Companion.getGalleryForPhotosAndPdfs(): Intent =
        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = MimeTypes.ANY.id
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf(MimeTypes.IMAGE_ANY.id, MimeTypes.APP_PDF.id))
        }

// Text
fun Intents.Companion.getOpenText(file: String): Intent = getOpenText(File(file))

fun Intents.Companion.getOpenText(file: File): Intent = getOpenText(Uri.fromFile(file))

fun Intents.Companion.getOpenText(uri: Uri): Intent = getPlayMedia(uri, MimeTypes.TEXT_ANY.id)

// Audio
fun Intents.Companion.getPlayAudioFile(uri: Uri): Intent = getPlayMedia(uri, MimeTypes.AUDIO_ANY.id)

fun Intents.Companion.getPlayAudioFile(file: File): Intent = getPlayMediaFile(file, MimeTypes.AUDIO_ANY.id)

fun Intents.Companion.getPlayAudioFile(path: String): Intent = getPlayMediaFile(path, MimeTypes.AUDIO_ANY.id)

fun Intents.Companion.getPlayAudio(url: String): Intent = getPlayMedia(url, MimeTypes.AUDIO_ANY.id)

// Image
fun Intents.Companion.getPlayImageFile(uri: Uri): Intent = getPlayMedia(uri, MimeTypes.IMAGE_ANY.id)

fun Intents.Companion.getPlayImageFile(file: File): Intent = getPlayMediaFile(file, MimeTypes.IMAGE_ANY.id)

fun Intents.Companion.getPlayImageFile(path: String): Intent = getPlayMediaFile(path, MimeTypes.IMAGE_ANY.id)

fun Intents.Companion.getPlayImage(url: String): Intent = getPlayMedia(url, MimeTypes.IMAGE_ANY.id)

// Video
fun Intents.Companion.getPlayVideoFile(uri: Uri): Intent = getPlayMedia(uri, MimeTypes.VIDEO_ANY.id)

fun Intents.Companion.getPlayVideoFile(file: File): Intent = getPlayMediaFile(file, MimeTypes.VIDEO_ANY.id)

fun Intents.Companion.getPlayVideoFile(path: String): Intent = getPlayMediaFile(path, MimeTypes.VIDEO_ANY.id)

fun Intents.Companion.getPlayVideo(url: String): Intent = getPlayMedia(url, MimeTypes.VIDEO_ANY.id)

// Media
fun Intents.Companion.getPlayMedia(url: String, type: String): Intent = getPlayMedia(Uri.parse(url), type)

fun Intents.Companion.getPlayMediaFile(file: File, type: String): Intent = getPlayMedia(Uri.fromFile(file), type)

fun Intents.Companion.getPlayMediaFile(path: String, type: String): Intent = getPlayMedia(Uri.fromFile(File(path)), type)

fun Intents.Companion.getPlayMedia(uri: Uri, type: String): Intent =
        Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, type)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

// Picture
fun Intents.Companion.getTakePicture(tempFile: File): Intent =
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile))
        }

fun Intents.Companion.getTakePicture(tempFile: String): Intent =
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(tempFile)))
        }

fun Intents.Companion.getSelectPicture(): Intent =
        Intent(Intent.ACTION_PICK).apply {
            type = MimeTypes.IMAGE_ANY.id
        }

fun Intents.Companion.isCropAvailable(context: Context): Boolean {
    val intent = Intent("com.android.camera.action.CROP")
    intent.type = MimeTypes.IMAGE_ANY.id
    return isIntentAvailable(context, intent)
}

fun Intents.Companion.getCropImage(context: Context, image: File, outputX: Int, outputY: Int, aspectX: Int, aspectY: Int, scale: Boolean): Intent {
    val intent = Intent("com.android.camera.action.CROP")
    val list = context.packageManager.queryIntentActivities(intent, 0)
    val res = list[0]
    return intent.apply {
        type = MimeTypes.IMAGE_ANY.id
        putExtra("outputX", outputX)
        putExtra("outputY", outputY)
        putExtra("aspectX", aspectX)
        putExtra("aspectY", aspectY)
        putExtra("scale", scale)
        putExtra("return-data", true)
        data = Uri.fromFile(image)
        component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
    }
}