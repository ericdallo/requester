package com.gregcodes.requester.list

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gregcodes.requester.Request
import com.gregcodes.requester.RequestRepository
import com.gregcodes.requester.room.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListRequestViewModel(application: Application) : AndroidViewModel(application) {

    private var requestList: MutableLiveData<List<Request>>? = null

    private val requestRepository: RequestRepository  by lazy {
        AppDatabase.getInstance(application).getRequestRepository()
    }

    fun getRequests() : MutableLiveData<List<Request>> {
        if (requestList == null) {
            requestList = MutableLiveData()
            getRequestsFromDatabase()
        }

        return requestList as MutableLiveData<List<Request>>
    }

    @SuppressLint("CheckResult")
    private fun getRequestsFromDatabase() {
        requestRepository.findAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d("size", it.size.toString())
                requestList!!.postValue(it)
            }
    }
}