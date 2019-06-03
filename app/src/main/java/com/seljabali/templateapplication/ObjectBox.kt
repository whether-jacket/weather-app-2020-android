package com.seljabali.templateapplication

import android.content.Context
import com.seljabali.templateapplication.models.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    private lateinit var boxStore: BoxStore

    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context).build()
    }

    fun get(): BoxStore = boxStore
}
