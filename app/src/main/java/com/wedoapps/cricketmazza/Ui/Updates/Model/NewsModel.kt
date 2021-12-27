package com.wedoapps.cricketmazza.Ui.Updates.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val id: String,
    val thumbnail: Int,
    val title: String,
    val article: String,
    val dateAndTime: String
) : Parcelable
