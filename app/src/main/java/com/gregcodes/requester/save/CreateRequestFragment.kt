package com.gregcodes.requester.save

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gregcodes.requester.R
import com.gregcodes.requester.Request
import com.gregcodes.requester.databinding.FragmentCreateRequestBinding


class CreateRequestFragment : Fragment() {

    private lateinit var viewModel: CreateRequestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBindings(savedInstanceState)

        viewModel.getSaveButtonClick().observe(this, Observer<CreateRequestFields> {
            val requestToSave = Request(it.name, it.protocol, it.address, it.verb, it.body)

            Log.d("request", requestToSave.toString())
        })
    }

    private fun initBindings(savedInstanceState: Bundle?) {
        val binding =
            DataBindingUtil.setContentView<FragmentCreateRequestBinding>(activity!!, R.layout.fragment_create_request)

        viewModel = ViewModelProviders.of(this).get(CreateRequestViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}