package com.wedoapps.cricketmazza.Ui.Live.Model

data class UpcomingLiveMatchData(
    var id: String,
    var seriesName: String,
    var timeAndDate: String,
    var matchType: String,
    var team1Flag: Int,
    var team1Name: String,
    var team1ShortName: String,
    var team1Market: String,
    var team1OversTitle: String,
    var team1Overs: String,
    var team1Percentage: Double,
    var team2Flag: Int,
    var team2Name: String,
    var team2ShortName: String,
    var team2Market: String,
    var team2OversTitle: String,
    var team2Overs: String,
    var team2Percentage: Double,
    var totalPercentage: Double,
    var percentageResult: String,

)
