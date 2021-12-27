package com.wedoapps.cricketmazza.Ui.Live.Model

data class LiveMatchData(
    var id: String,
    var seriesName: String,
    var team1Flag: Int,
    var team1Name: String,
    var team1Score: String,
    var team1Over: String,
    var team2Flag: Int,
    var team2Name: String,
    var team2Score: String,
    var team2Over: String,
    var team1Rating: Int,
    var team2Rating: Int,
    var crr: String,
    var favTeamName: String,
    var rrr: String,
    var matchStatus: String,
)
