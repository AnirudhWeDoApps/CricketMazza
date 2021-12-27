package com.wedoapps.cricketmazza.Ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wedoapps.cricketmazza.Repository.CricketMazzaRepository
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Ui.MatchDetails.Model.Info
import com.wedoapps.cricketmazza.Utils.Resource

class CricketMazzaViewModel(
    val app: Application,
    private val repository: CricketMazzaRepository
) : AndroidViewModel(app) {

    fun getHomeRecentMatch(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.homeGetRecentMatches()
    }

    fun getCompletedMatches(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.getCompletedMatches()
    }

    fun getUpcomingMatches(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.getUpcomingMatches()
    }

    fun getLiveMatch(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.getLiveMatches()
    }

    fun getMatch(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.getMatch()
    }

    fun getRecentTestMatches(): LiveData<Resource<MutableList<RecentMatchData>>> {
        return repository.getRecentTestMatches()
    }

    fun getInfo(id: String): LiveData<Resource<Info>> {
        return repository.getInfo(id)
    }
}