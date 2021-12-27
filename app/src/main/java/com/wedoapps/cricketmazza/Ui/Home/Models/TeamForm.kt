package com.wedoapps.cricketmazza.Ui.Home.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamForm(
    val NameTeam1: String? = "",
    val NameTeam2: String? = "",
    val FormTeam1: String? = "",
    val FormTeam2: String? = "",
) : Parcelable
