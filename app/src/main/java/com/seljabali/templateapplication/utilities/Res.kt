package com.seljabali.templateapplication.utilities

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.util.TypedValue
import androidx.annotation.*
import androidx.core.content.ContextCompat

object Res {

    private const val POINT = "."
    private const val R = "R"
    private const val JOIN = "$"
    private const val DRAWABLE = "drawable"
    private const val STRING = "string"

    @JvmStatic
    fun getIdentifier(context: Context, @AnyRes id: Int): String? = context.resources.getResourceEntryName(id)

    @JvmStatic
    fun getPath(context: Context, resFolder: String, resourceId: Int): String =
        "android.resource://${context.packageName}/$resFolder/${getIdentifier(context, resourceId)}"

    @JvmStatic
    fun getPathUri(context: Context, resFolder: String, resourceId: Int): Uri =
        Uri.parse(getPath(context, resFolder, resourceId))

    @JvmStatic
    fun getColorHex(context: Context, @ColorRes id: Int): String {
        val colorHex = Integer.toHexString(ContextCompat.getColor(context, id) and 0x00ffffff)
        return "#${colorHex.toUpperCase()}"
    }

    @JvmStatic
    fun getColorArray(context: Context, @ArrayRes id: Int): IntArray {
        return try {
            val ta = context.resources.obtainTypedArray(id)
            val colors = IntArray(ta.length())
            for (i in 0 until ta.length()) {
                colors[i] = ta.getResourceId(i, 0)
            }
            ta.recycle()
            colors
        } catch (e: Exception) {
            IntArray(0)
        }
    }

    @JvmStatic
    fun getFloat(context: Context, @AnyRes id: Int): Float {
        return try {
            val outValue = TypedValue()
            context.resources.getValue(id, outValue, true)
            outValue.float
        } catch (e: Exception) {
            0f
        }
    }

    @JvmStatic
    fun getInteger(context: Context, @IntegerRes id: Int): Int {
        return try {
            context.resources.getInteger(id)
        } catch (e: Exception) {
            -1
        }
    }

    @JvmStatic
    fun getTypeface(context: Context, path: String): Typeface = Typeface.createFromAsset(context.assets, path)

    @JvmStatic
    fun getPx(context: Context, @DimenRes dpDimenId: Int): Float = context.resources.getDimension(dpDimenId)

    @JvmStatic
    fun getRoundedPx(context: Context, @DimenRes dpDimenId: Int): Int =
        context.resources.getDimensionPixelSize(dpDimenId)

    @JvmStatic
    fun getDp(context: Context, @DimenRes dpDimenId: Int): Float =
        getPx(context, dpDimenId) / context.resources.displayMetrics.density

    /**
     *  Get by Name
     */
    fun getDrawable(context: Context, name: String): Int =
        try {
            Class.forName(context.packageName + POINT + R + JOIN + DRAWABLE).getDeclaredField(name).get(null) as Int
        } catch (e: Exception) {
            -1
        }

    fun getString(context: Context, name: String): String =
        try {
            context.resources.getString(context.resources.getIdentifier(name, STRING, context.packageName))
        } catch (e: Exception) {
            ""
        }
}