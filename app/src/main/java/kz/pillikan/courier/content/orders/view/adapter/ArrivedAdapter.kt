package kz.pillikan.courier.content.orders.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.pillikan.courier.R
import kz.pillikan.courier.content.orders.model.response.arrived.ArrivedResponse
import kz.pillikan.courier.content.orders.view.DetailPageFragment

class ArrivedAdapter : RecyclerView.Adapter<ArrivedAdapter.ViewHolder> {

    private var callback: DetailPageFragment

    private val arrivedList: ArrayList<ArrivedResponse> = ArrayList()

    constructor(callback: DetailPageFragment) : super() {
        this.callback = callback
    }

    fun addList(arrivedList: ArrayList<ArrivedResponse>) {
        this.arrivedList.clear()
        this.arrivedList.addAll(arrivedList)
        notifyDataSetChanged()

    }

    class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        private val foodFirst = root.findViewById<TextView>(R.id.tv_food)
        private val firstSum = root.findViewById<TextView>(R.id.tv_food_sum)
        fun bind(arrived: ArrivedResponse, callback: DetailPageFragment) {
            foodFirst.text = arrived.food
            firstSum.text = arrived.foodSum.toString()
        }
    }

        override fun getItemCount(): Int = arrivedList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(arrived = arrivedList[position], callback)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_oreder_list, parent, false)
            return ViewHolder(root)
        }
    }


