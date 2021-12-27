package com.wedoapps.cricketmazza.Ui.Recents.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecentDataModel(
    var id: String,
    var seriesName: String,
    var matchNoAndLocation: String,
    var team1Flag: Int,
    var team1Name: String,
    var team1ScoreAndOver: String,
    var team2Flag: Int,
    var team2Name: String,
    var team2ScoreAndOver: String,
    var timeAndDate: String,
    var status: String
) : Parcelable