package com.wedoapps.cricketmazza.Utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wedoapps.cricketmazza.Repository.CricketMazzaRepository
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel

class ViewModelProviderFactory(
    private val app: Application,
    private val repository: CricketMazzaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CricketMazzaViewModel(app, repository) as T
    }
}