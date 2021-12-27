package com.wedoapps.cricketmazza.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Ui.MatchDetails.Model.Info
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource

class CricketMazzaRepository {

    private var firestore = FirebaseFirestore.getInstance()
    private val firestoreRef = firestore.collection("MatchList")
    private var mutableLiveDataRecentMatchData =
        MutableLiveData<Resource<MutableList<RecentMatchData>>>()
    private var mutableLiveDataUpcomingData =
        MutableLiveData<Resource<MutableList<RecentMatchData>>>()
    private var mutableLiveDataLiveData =
        MutableLiveData<Resource<MutableList<RecentMatchData>>>()
    private var mutableLiveDataCompletedData =
        MutableLiveData<Resource<MutableList<RecentMatchData>>>()
    private var mutableLiveDataRecentTestData =
        MutableLiveData<Resource<MutableList<RecentMatchData>>>()
    private var recentMatchData = RecentMatchData()

    fun getMatch(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {

        firestore.collection("MatchList")
            .whereLessThan("MatchIndex", 100)
            .orderBy("MatchIndex", Query.Direction.ASCENDING)
            .limit(10)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w(TAG, "Listen Failed", error)
                    return@addSnapshotListener
                }

                if (value != null) {
                    val allMatch = arrayListOf<RecentMatchData>()
                    val match = value.documents
                    match.forEach {
                        Log.d(TAG, "getMatch: ${it.data}")
                        recentMatchData = it.toObject(RecentMatchData::class.java)!!
                        recentMatchData.id = it.id
                        //                        getScore(it.id)
                        allMatch.add(recentMatchData)
                    }
                    mutableLiveDataRecentMatchData.value = Resource.Success(allMatch)
                } else {
                    Log.d(TAG, "No Data")
                }
            }
        return mutableLiveDataRecentMatchData
    }

    fun homeGetRecentMatches(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {
        firestore.collection("MatchList")
            .whereLessThan("MatchIndex", 100)
            .orderBy("MatchIndex", Query.Direction.ASCENDING)
            .limit(10)
            .get()
            .addOnCompleteListener { task ->
                val recentMatchDataList = arrayListOf<RecentMatchData>()
                mutableLiveDataRecentMatchData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val documents = task.result
                    documents!!.forEach { data ->
                        recentMatchData = data.toObject(RecentMatchData::class.java)
                        recentMatchData.id = data.id
                        recentMatchDataList.add(recentMatchData)
                    }
                    Log.d(TAG, "getRecentMatchList: $recentMatchDataList")
                    mutableLiveDataRecentMatchData.value = Resource.Success(recentMatchDataList)
                } else {
                    mutableLiveDataRecentMatchData.value = Resource.Error("NO DATA")
                }
            }
            .addOnFailureListener { error ->
                mutableLiveDataRecentMatchData.value = error.message.let {
                    Resource.Error(it.toString())
                }
            }
        return mutableLiveDataRecentMatchData
    }

    fun getCompletedMatches(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {
        firestore.collection("MatchList")
            .whereEqualTo("MatchStatus", "COMPLETED")
            .orderBy("MatchDate", Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .addOnCompleteListener { task ->
                val recentMatchList = arrayListOf<RecentMatchData>()
                mutableLiveDataCompletedData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val documents = task.result
                    documents!!.forEach { data ->
                        recentMatchData = data.toObject(RecentMatchData::class.java)
                        recentMatchData.id = data.id
                        recentMatchList.add(recentMatchData)
                    }
                    Log.d(TAG, "getCompletedMatchesList: $recentMatchList")
                    mutableLiveDataCompletedData.value = Resource.Success(recentMatchList)
                } else {
                    mutableLiveDataCompletedData.value = Resource.Error("NO DATA")
                }
            }
            .addOnFailureListener {
                mutableLiveDataCompletedData.value = it.message.let { error ->
                    Resource.Error(error.toString())
                }
            }
        return mutableLiveDataCompletedData
    }

    fun getUpcomingMatches(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {
        firestore.collection("MatchList")
            .whereEqualTo("MatchStatus", "UPCOMING")
            .orderBy("MatchDate", Query.Direction.ASCENDING)
            .limit(10)
            .get()
            .addOnCompleteListener { task ->
                val recentMatchList = arrayListOf<RecentMatchData>()
                mutableLiveDataUpcomingData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val documents = task.result
                    documents!!.forEach { data ->
                        recentMatchData = data.toObject(RecentMatchData::class.java)
                        recentMatchData.id = data.id
                        recentMatchList.add(recentMatchData)
                    }
                    Log.d(TAG, "getUpcomingMatchesList: $recentMatchList")
                    mutableLiveDataUpcomingData.value = Resource.Success(recentMatchList)
                } else {
                    mutableLiveDataUpcomingData.value = Resource.Error("NO DATA")
                }
            }
            .addOnFailureListener {
                mutableLiveDataUpcomingData.value = it.message.let { error ->
                    Resource.Error(error.toString())
                }
            }
        return mutableLiveDataUpcomingData
    }

    fun getLiveMatches(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {
        firestore.collection("MatchList")
            .whereEqualTo("MatchStatus", "LIVE")
            .orderBy("MatchDate", Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .addOnCompleteListener { task ->
                val recentMatchList = arrayListOf<RecentMatchData>()
                mutableLiveDataLiveData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val documents = task.result
                    documents!!.forEach { data ->
                        recentMatchData = data.toObject(RecentMatchData::class.java)
                        recentMatchData.id = data.id
                        recentMatchList.add(recentMatchData)
                    }
                    Log.d(TAG, "getLiveMatchesList: $recentMatchList")
                    mutableLiveDataLiveData.value = Resource.Success(recentMatchList)
                } else {
                    mutableLiveDataLiveData.value = Resource.Error("NO DATA")
                }
            }
            .addOnFailureListener {
                mutableLiveDataLiveData.value = it.message.let { error ->
                    Resource.Error(error.toString())
                }
            }
        return mutableLiveDataLiveData
    }

    fun getRecentTestMatches(): MutableLiveData<Resource<MutableList<RecentMatchData>>> {
        firestore.collection("MatchList")
            .whereEqualTo("MatchStatus", "COMPLETED")
            .whereEqualTo("MatchType", "0")
            .orderBy("MatchDate", Query.Direction.ASCENDING)
            .limit(10)
            .get()
            .addOnCompleteListener { task ->
                val recentMatchList = arrayListOf<RecentMatchData>()
                mutableLiveDataRecentTestData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val documents = task.result
                    documents!!.forEach { data ->
                        recentMatchData = data.toObject(RecentMatchData::class.java)
                        recentMatchData.id = data.id
                        recentMatchList.add(recentMatchData)
                    }
                    Log.d(TAG, "getRecentTestMatches: $recentMatchList")
                    mutableLiveDataRecentTestData.value = Resource.Success(recentMatchList)
                } else {
                    mutableLiveDataRecentTestData.value = Resource.Error("NO DATA")
                }
            }
            .addOnFailureListener {
                mutableLiveDataRecentTestData.value = it.message.let { error ->
                    Resource.Error(error.toString())
                }
            }
        return mutableLiveDataRecentTestData
    }

    fun getInfo(id: String): MutableLiveData<Resource<Info>> {
        val infoMutableLiveData = MutableLiveData<Resource<Info>>()
        infoMutableLiveData.value = Resource.Loading()
        firestoreRef.document(id)
            .get()
            .addOnCompleteListener { task ->
                infoMutableLiveData.value = Resource.Loading()
                if (task.isSuccessful) {
                    val data = task.result
                    Log.d(TAG, "getInfo: $data")
                    val info = data?.toObject(Info::class.java)
                    infoMutableLiveData.value = Resource.Success(info)
                } else {
                    infoMutableLiveData.value = Resource.Error("NO DATA")
                }
            }.addOnFailureListener {
                infoMutableLiveData.value = it.message.let { error ->
                    Resource.Error(error.toString())
                }
            }
        return infoMutableLiveData
    }

}