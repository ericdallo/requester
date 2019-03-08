package com.gregcodes.requester.save

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.gregcodes.requester.BR
import com.gregcodes.requester.R
import com.gregcodes.requester.protocol.Protocol
import com.gregcodes.requester.verb.HTTPVerb


class CreateRequestFields: BaseObservable() {

    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }
    var address: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }
    var errorName: ObservableField<Int> = ObservableField()
    var errorAddress: ObservableField<Int> = ObservableField()

    var protocol: Protocol = Protocol.HTTP
    var verb: HTTPVerb = HTTPVerb.GET
    var body: String = ""

    fun setProtocol(protocol: String) {
        this.protocol = Protocol.valueOf(protocol)
    }

    fun onVerbChange(verb: String) {
        this.verb = HTTPVerb.valueOf(verb)
        notifyPropertyChanged(BR.bodyVisibility)
    }

    @Bindable
    fun isValid(): Boolean {
        var hasError = false
        if (validateName(false)) {
            hasError = true
        }

        if (validateAddress(false)) {
            hasError = true
        }

        return hasError
    }

    fun validateName(setMessage: Boolean) : Boolean {
        if (name.isEmpty()) {
            if (setMessage) errorName.set(R.string.invalid_request_name)
            return true
        }

        errorName.set(null)
        return false
    }

    fun validateAddress(setMessage: Boolean) : Boolean {
        if (address.isEmpty()) {
            if (setMessage) errorAddress.set(R.string.invalid_request_name)
            return true
        }

        errorAddress.set(null)
        return false
    }

    @Bindable
    fun getBodyVisibility(): Int {
        if (verb != HTTPVerb.GET) {
            return View.VISIBLE
        }
        return View.INVISIBLE
    }

}
