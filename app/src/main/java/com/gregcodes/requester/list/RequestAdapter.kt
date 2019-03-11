package com.gregcodes.requester.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gregcodes.requester.R
import com.gregcodes.requester.Request
import kotlinx.android.synthetic.main.item_row_request.view.*

class RequestAdapter(items: List<Request>, private val context: Context) : Adapter<RequestAdapter.RequestViewHolder>() {

    val items: ArrayList<Request> = ArrayList(items)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_request, parent, false)

        return RequestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bindingvalues(items[position])
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Request, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindingvalues(request: Request) {
            itemView.requestName.text = request.name
            itemView.requestAddress.text = request.address
        }
    }
}