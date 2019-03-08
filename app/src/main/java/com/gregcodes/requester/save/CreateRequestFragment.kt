package com.gregcodes.requester.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gregcodes.requester.MainActivity
import com.gregcodes.requester.R
import com.gregcodes.requester.databinding.FragmentCreateRequestBinding
import kotlinx.android.synthetic.main.fragment_create_request.*


class CreateRequestFragment : Fragment() {

    private lateinit var viewModel: CreateRequestViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initBindings()
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestGetVerb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                requestBodyLayout.visibility = View.INVISIBLE
            }
        }

        requestPostVerb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(function = hideRequestBody()))
        requestPutVerb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(function = hideRequestBody()))
        requestPatchVerb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(function = hideRequestBody()))
        requestDeleteVerb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(function = hideRequestBody()))
    }

    private fun initBindings() {
        val binding = DataBindingUtil.setContentView<FragmentCreateRequestBinding>(activity!!, R.layout.fragment_create_request)

        viewModel = ViewModelProviders.of(this).get(CreateRequestViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun hideRequestBody(): (buttonView: CompoundButton, isChecked: Boolean) -> Unit = { _, isChecked ->
        if (isChecked) {
            requestBodyLayout.visibility = View.VISIBLE
        }
    }
}