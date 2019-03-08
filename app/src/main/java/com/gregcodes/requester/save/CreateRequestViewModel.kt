package com.gregcodes.requester.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateRequestViewModel : ViewModel() {

    public lateinit var name: MutableLiveData<String>
    public lateinit var address: MutableLiveData<String>

    fun onSaveClicked() {

    }
}