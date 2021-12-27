package com.wedoapps.cricketmazza.Utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.wedoapps.cricketmazza.R

fun MaterialTextView.setBgSelected(context: Context, viewToHideBg: MaterialTextView): Boolean {
    this.background =
        ContextCompat.getDrawable(context, R.drawable.bg_t_black_rounded_tab_look_clear)
    this.setTextColor(
        ContextCompat.getColor(
            context,
            R.color.white
        )
    )
    viewToHideBg.setBgUnSelected(context)
    return true
}

fun MaterialTextView.setBgUnSelected(context: Context): Boolean {
    this.background =
        null
    this.setTextColor(
        ContextCompat.getColor(
            context,
            android.R.color.darker_gray
        )
    )
    return false
}