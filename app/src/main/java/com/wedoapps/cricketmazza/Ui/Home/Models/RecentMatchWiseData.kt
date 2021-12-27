package com.wedoapps.cricketmazza.Ui.Home.Models

data class RecentMatchWiseData(
    val id: String,
    val isSeriesLive: Boolean,
    val seriesType: String,
    val seriesName: String,
    val team1Flag: Int,
    val team1Name: String,
    val team1Score: String,
    val team2Flag: Int,
    val team2Name: String,
    val team2Score: String,
    val totalMatches: Int,
    val totalLeft: Int,
    val startData: String,
    val endDate: String,
)