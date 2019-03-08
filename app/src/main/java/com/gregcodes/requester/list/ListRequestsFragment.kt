package com.gregcodes.requester.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gregcodes.requester.R
import kotlinx.android.synthetic.main.fragment_list_requests.*


class ListRequestsFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRequestButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listRequestsFragment_to_createRequestFragment))
    }
}
