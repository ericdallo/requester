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
import androidx.navigation.Navigation
import com.gregcodes.requester.R
import com.gregcodes.requester.Request
import com.gregcodes.requester.RequestRepository
import com.gregcodes.requester.databinding.FragmentCreateRequestBinding
import com.gregcodes.requester.databinding.FragmentListRequestsBinding
import com.gregcodes.requester.list.ListRequestViewModel
import com.gregcodes.requester.room.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CreateRequestFragment : Fragment() {

    private lateinit var viewModel: CreateRequestViewModel
    private lateinit var requestRepository: RequestRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateRequestBinding>(inflater,  R.layout.fragment_create_request, container, false)

        viewModel = ViewModelProviders.of(this).get(CreateRequestViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestRepository = AppDatabase.getInstance(context!!).getRequestRepository()

        viewModel.getSaveButtonClick().observe(this, Observer<CreateRequestFields> {
            val requestToSave = Request(it.name, it.protocol, it.address, it.verb, it.body)

            requestRepository.save(requestToSave)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.d("navigation", "going to home")
                    Navigation.findNavController(view).navigate(R.id.listRequestsFragment)
                }
        })
    }
}