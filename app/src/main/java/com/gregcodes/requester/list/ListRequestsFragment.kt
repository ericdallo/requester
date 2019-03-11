package com.gregcodes.requester.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_requests.*
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.NonNull
import com.gregcodes.requester.R
import com.gregcodes.requester.RequestRepository
import com.gregcodes.requester.room.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ListRequestsFragment : Fragment() {

    private lateinit var viewModel: ListRequestViewModel
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var requestRepository: RequestRepository

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestRepository = AppDatabase.getInstance(context!!).getRequestRepository()

        viewModel = ViewModelProviders.of(this).get(ListRequestViewModel::class.java)

        rvRequests.layoutManager = LinearLayoutManager(context)

        viewModel.getRequests().observe(this, Observer {
            requestAdapter = RequestAdapter(it, context!!)
            rvRequests.adapter = requestAdapter
        })

        addRequestButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createRequestFragment))

        enableSwipeToDelete()
    }

    private fun enableSwipeToDelete() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(context!!) {

            @SuppressLint("CheckResult")
            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {

                val position = viewHolder.adapterPosition
                val item = requestAdapter.items[position]

                requestRepository.delete(item)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        requestAdapter.removeItem(position)

                        Snackbar.make(listFragmentsLayout, "Request removed.", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                requestRepository.save(item)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe {
                                        requestAdapter.restoreItem(item, position)
                                        rvRequests.scrollToPosition(position)
                                    }
                            }
                            .setActionTextColor(Color.YELLOW)
                            .show()
                    }

            }
        }

        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(rvRequests)
    }

}
