package com.gregcodes.requester

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("error")
fun bindErrorMessage(textInputLayout: TextInputLayout, strOrResId: Any?) {
    if (strOrResId == null) {
        textInputLayout.error = null
        return
    }
    if (strOrResId is Int) {
        textInputLayout.error = textInputLayout.context.getString(strOrResId)
    } else {
        textInputLayout.error = strOrResId as String
    }
}

@BindingAdapter("onFocus")
fun bindFocusChange(textInputEditText: TextInputEditText, onFocusChangeListener: View.OnFocusChangeListener) {
    if (textInputEditText.onFocusChangeListener == null) {
        textInputEditText.onFocusChangeListener = onFocusChangeListener
    }
}