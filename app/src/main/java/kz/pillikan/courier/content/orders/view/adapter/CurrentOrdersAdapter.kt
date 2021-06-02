package kz.pillikan.courier.content.orders.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.pillikan.courier.R
import kz.pillikan.courier.content.orders.model.response.order.OrdersResponse
import kz.pillikan.courier.content.orders.view.OrdersFragment

class CurrentOrdersAdapter : RecyclerView.Adapter<CurrentOrdersAdapter.ViewHolder> {

    private var callback: OrdersFragment

    private val currentOrdersList: ArrayList<OrdersResponse> = ArrayList()

    constructor(callback: OrdersFragment) : super(){
        this.callback = callback
    }
    fun addList(orderList: ArrayList<OrdersResponse>){
        this.currentOrdersList.clear()
        this.currentOrdersList.addAll(orderList)
        notifyDataSetChanged()

    }

    class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        private val tvArrival = root.findViewById<TextView>(R.id.tv_arrival)
        private val tvTime = root.findViewById<TextView>(R.id.tv_time)
        private val tvNumberOrder = root.findViewById<TextView>(R.id.tv_number_order)
        private val tvNameRetail = root.findViewById<TextView>(R.id.tv_name_retail)
        private val tvAddress = root.findViewById<TextView>(R.id.tv_address)
        private val tvTimeOrder = root.findViewById<TextView>(R.id.tv_time_order)

        fun bind(order: OrdersResponse, callback: OrdersFragment){
            tvArrival.text = order.arrival
            tvTime.text = order.time
            tvNumberOrder.text = order.numberOrder
            tvNameRetail.text = order.nameRetail
            tvAddress.text = order.address
            tvTimeOrder.text = order.timeOrder
            root.setOnClickListener {
                callback.onClickCurrentItem(order)
            }
        }

    }

    override fun getItemCount(): Int = currentOrdersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(order = currentOrdersList[position],callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return ViewHolder(root)
    }
}

