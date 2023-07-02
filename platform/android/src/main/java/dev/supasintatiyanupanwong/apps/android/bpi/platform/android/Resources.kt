package dev.supasintatiyanupanwong.apps.android.bpi.platform.android

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
fun Context.attrColorOf(@AttrRes attrId: Int): Int {
    val arr = obtainStyledAttributes(null, intArrayOf(attrId))
    val color = arr.getColor(0, Color.TRANSPARENT)
    arr.recycle()
    return color
}
