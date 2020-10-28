package com.seljabali.core.utilities.intents

import android.content.Intent
import android.net.Uri
import com.seljabali.core.utilities.MimeTypes

open class ShareIntentBuilder {
    var intent: Intent
        private set

    init {
        intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = MimeTypes.ANY.id
    }

    fun withAddresses(addresses: Array<String>?): ShareIntentBuilder {
        if (addresses != null && addresses.isNotEmpty()) intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        return this
    }

    fun withSubject(subject: String?): ShareIntentBuilder {
        if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        return this
    }

    fun withText(text: String?): ShareIntentBuilder {
        if (text != null) intent.putExtra(Intent.EXTRA_TEXT, text)
        return this
    }

    fun withAttachment(resourceUri: Uri?): ShareIntentBuilder {
        if (resourceUri!= null) intent.putExtra(Intent.EXTRA_STREAM, resourceUri)
        return this
    }

    fun withExplicitMimeType(mimeType: String?): ShareIntentBuilder {
        if (mimeType != null) intent.type = mimeType
        return this
    }

    fun withChooserTitle(chooserTitle: String?): ShareIntentBuilder {
        if (chooserTitle != null) intent = Intent.createChooser(intent, chooserTitle)
        return this
    }

    fun build(): Intent {
        return intent
    }
}