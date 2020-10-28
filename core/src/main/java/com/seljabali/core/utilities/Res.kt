package com.seljabali.core.utilities

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import androidx.annotation.*
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object Res {

    private const val POINT = "."
    private const val R = "R"
    private const val JOIN = "$"
    private const val DRAWABLE = "drawable"
    private const val MIPMAP = "mipmap"
    private const val STRING = "string"

    @JvmStatic
    fun getIdentifier(context: Context, @AnyRes id: Int): String? = context.resources.getResourceEntryName(id)

    @JvmStatic
    @AnyRes
    fun getResIdFromAttribute(context: Context, @AttrRes attr: Int): Int {
        if (attr == 0) return 0
        val typedValueAttr = TypedValue()
        context.theme.resolveAttribute(attr, typedValueAttr, true)
        return typedValueAttr.resourceId
    }

    @JvmStatic
    fun getPath(context: Context, resFolder: String, @AnyRes resourceId: Int): String =
        "android.resource://${context.packageName}/$resFolder/${getIdentifier(context, resourceId)}"

    @JvmStatic
    fun getPathUri(context: Context, resFolder: String, @AnyRes resourceId: Int): Uri =
        Uri.parse(getPath(context, resFolder, resourceId))

    @JvmStatic
    fun getColorHex(context: Context, @ColorRes id: Int): String {
        val colorHex = Integer.toHexString(getColor(context, id) and 0x00ffffff)
        return "#${colorHex.toUpperCase()}"
    }

    @JvmStatic
    fun getColor(context: Context, @ColorRes id: Int): Int = ContextCompat.getColor(context, id)

    @JvmStatic
    @ColorInt
    fun getColorViaAttr(context: Context, @AttrRes id: Int): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(id, typedValue, true)
        return typedValue.data
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
    fun getPx(context: Context, dp: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)

    @JvmStatic
    fun getRoundedPx(context: Context, @DimenRes dpDimenId: Int): Int = context.resources.getDimensionPixelSize(dpDimenId)

    @JvmStatic
    fun getDp(context: Context, @DimenRes dpDimenId: Int): Float = getPx(context, dpDimenId) / context.resources.displayMetrics.density

    @JvmStatic
    fun getRawTextFile(context: Context, @RawRes resId: Int): String? {
        val inputStream = context.resources.openRawResource(resId)
        val inputReader = InputStreamReader(inputStream)
        val bufferReader = BufferedReader(inputReader)
        var line: String?
        val text = StringBuilder()
        try {
            line = bufferReader.readLine()
            while (line != null) {
                text.append(line)
                    .append('\n')
                line = bufferReader.readLine()
            }
        } catch (e: IOException) {
            return null
        }
        return text.toString()
    }

    @JvmStatic
    fun getDrawable(context: Context, @DrawableRes drawableId: Int): Drawable? = context.getDrawable(drawableId)

    @JvmStatic
    fun getString(context: Context, @StringRes id: Int): String =
        try {
            context.resources.getString(id)
        } catch (e: Exception) {
            ""
        }

    @JvmStatic
    fun getStringFormatted(context: Context, @StringRes id: Int, vararg formatArgs: Any): String =
        try {
            context.resources.getString(id, *formatArgs)
        } catch (e: Exception) {
            ""
        }

    @JvmStatic
    fun getQuantityStringFormatted(context: Context, @PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String =
        try {
            context.resources.getQuantityString(id, quantity, *formatArgs)
        } catch (e: Exception) {
            ""
        }

    @JvmStatic
    fun getStringArray(context: Context, @ArrayRes id: Int): Array<String> {
        try {
            return context.resources.getStringArray(id)
        } catch (e: Exception) {
        }
        return emptyArray()
    }

    /**
     *  Get by Name
     */
    @JvmStatic
    @DrawableRes
    fun getDrawable(context: Context, name: String): Int =
        try {
            Class.forName(context.packageName + POINT + R + JOIN + DRAWABLE).getDeclaredField(name).get(null) as Int
        } catch (e: Exception) {
            -1
        }

    @JvmStatic
    @DrawableRes
    fun getMipmap(context: Context, name: String): Int =
        try {
            Class.forName(context.packageName + POINT + R + JOIN + MIPMAP).getDeclaredField(name).get(null) as Int
        } catch (e: Exception) {
            -1
        }

    @JvmStatic
    fun getString(context: Context, name: String): String =
        try {
            context.resources.getString(context.resources.getIdentifier(name, STRING, context.packageName))
        } catch (e: Exception) {
            ""
        }
}