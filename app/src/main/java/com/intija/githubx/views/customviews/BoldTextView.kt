package com.intija.githubx.views.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

//Custom TextView for efficient font style changes across application
class BoldTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {
        applyTypeface(context, "bold.ttf")

    }

    private fun applyTypeface(context: Context, d: String) {
        val tr = Typeface.createFromAsset(context.assets, d)
        typeface = tr
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyTypeface(context, "bold.ttf")
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        applyTypeface(context, "bold.ttf")
    }
}
