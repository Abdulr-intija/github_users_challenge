package com.intija.githubx.views.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

//Custom TextView for efficient font style changes across application
class NormalTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {
        applyTypeface(context, "normal.ttf")
    }

    private fun applyTypeface(context: Context, d: String) {
        val tr = Typeface.createFromAsset(getContext().assets, d)
        typeface = tr
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyTypeface(context, "normal.ttf")
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        applyTypeface(context, "normal.ttf")
    }


}
