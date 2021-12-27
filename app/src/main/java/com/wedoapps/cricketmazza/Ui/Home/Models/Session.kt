package com.wedoapps.cricketmazza.Ui.Home.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val Min: String? = "",
    val Max: String? = "",
    val Complete: String? = "",
    val Name: String? = "",
    val Open: String? = ""
) : Parcelable
