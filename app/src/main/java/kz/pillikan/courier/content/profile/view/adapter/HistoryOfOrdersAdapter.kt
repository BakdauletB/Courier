package kz.pillikan.courier.content.profile.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.pillikan.courier.R
import kz.pillikan.courier.content.profile.model.HistoryOfOrdersResponse
import kz.pillikan.courier.content.profile.view.EarningsFragment

class HistoryOfOrdersAdapter: RecyclerView.Adapter<HistoryOfOrdersAdapter.ViewHolder> {

    private var callback: EarningsFragment

    private var historyList: ArrayList<HistoryOfOrdersResponse> = ArrayList()

    constructor(callback: EarningsFragment) : super() {
        this.callback = callback
    }

    fun addList(historyList: List<HistoryOfOrdersResponse>) {
        this.historyList.clear()
        this.historyList.addAll(historyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history_of_orders, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(history = historyList[position], callback)
    }

    override fun getItemCount(): Int = historyList.size

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val tvNumber = root.findViewById<TextView>(R.id.tv_numbers)
        private val tvRetail = root.findViewById<TextView>(R.id.tv_retail)

        @SuppressLint("SetTextI18n")
        fun bind(history: HistoryOfOrdersResponse, callback: EarningsFragment) {
            tvNumber.text = history.number.toString()
            tvRetail.text = history.retail
        }
    }

}