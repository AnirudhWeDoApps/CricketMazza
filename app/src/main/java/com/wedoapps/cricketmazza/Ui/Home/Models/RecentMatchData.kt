package com.wedoapps.cricketmazza.Ui.Home.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecentMatchData(
    var id: String? = "",
    val MatchDetail: String? = "",
    val NoOfMatch: String? = "",
    val Series: String? = "",
    val Venue: String? = "",
    val CurrentDate: String? = "",
    val MatchStatus: String? = "",
    val Team2: String? = "",
    val Team1: String? = "",
    val MatchResult: String? = "",
    val IsShowFlag: Boolean? = false,
    val IsToss: Boolean? = false,
    val MatchDate: Long? = null,
    val SessionHistoryInfo1: List<Session>? = null,
    val SessionHistoryInfo2: List<Session>? = null,
    val TeamForm: TeamForm? = null
): Parcelable