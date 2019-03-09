package com.gregcodes.requester.save

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CreateRequestViewModel : ViewModel() {

    private lateinit var request: CreateRequestFields
    private lateinit var onFocusName: View.OnFocusChangeListener
    private lateinit var onFocusAddress: View.OnFocusChangeListener

    private val saveButtonClick = MutableLiveData<CreateRequestFields>()

    fun init() {
        request = CreateRequestFields()

        onFocusName = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                request.validateName(true)
            }
        }

        onFocusAddress = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                request.validateAddress(true)
            }
        }
    }

    fun getRequest(): CreateRequestFields {
        return request
    }

    fun getSaveButtonClick(): MutableLiveData<CreateRequestFields> {
        return saveButtonClick
    }

    fun onSaveButtonClick() {
        if (request.isValid()) {
            saveButtonClick.value = request
        }
    }

    fun getNameOnFocusChangeListener(): View.OnFocusChangeListener {
        return onFocusName
    }

    fun getAddressOnFocusChangeListener(): View.OnFocusChangeListener {
        return onFocusAddress
    }


}