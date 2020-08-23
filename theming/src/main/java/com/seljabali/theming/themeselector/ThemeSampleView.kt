package com.seljabali.theming.themeselector

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.seljabali.theming.R

class ThemeSampleView : FrameLayout {

    constructor(@NonNull context: Context) : super(context) {
        init()
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.view_theme_sample, this)
    }
}