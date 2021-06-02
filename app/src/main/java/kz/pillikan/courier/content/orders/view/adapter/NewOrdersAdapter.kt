package kz.pillikan.courier.content.orders.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.pillikan.courier.R
import kz.pillikan.courier.content.orders.model.response.order.OrdersResponse
import kz.pillikan.courier.content.orders.view.OrdersFragment

class NewOrdersAdapter : RecyclerView.Adapter<NewOrdersAdapter.ViewHolder> {

    private var callback: OrdersFragment

    private val newOrdersList: ArrayList<OrdersResponse> = ArrayList()

    constructor(callback: OrdersFragment) : super(){
        this.callback = callback
    }
    fun addList(newList: ArrayList<OrdersResponse>){
        this.newOrdersList.clear()
        this.newOrdersList.addAll(newList)
        notifyDataSetChanged()

    }

    class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        private val arrival = root.findViewById<TextView>(R.id.tv_arrival)
        private val time = root.findViewById<TextView>(R.id.tv_time)
        private val numberOrder = root.findViewById<TextView>(R.id.tv_number_order)
        private val nameRetail = root.findViewById<TextView>(R.id.tv_name_retail)
        private val address = root.findViewById<TextView>(R.id.tv_address)
        private val timeOrder = root.findViewById<TextView>(R.id.tv_time_order)

        fun bind(newOrder: OrdersResponse, callback: OrdersFragment){
            arrival.text = newOrder.arrival
            time.text = newOrder.time
            numberOrder.text = newOrder.numberOrder
            nameRetail.text = newOrder.nameRetail
            address.text = newOrder.address
            timeOrder.text = newOrder.timeOrder
            root.setOnClickListener {
                callback.onClickNewItem(newOrder)
            }
        }

    }

    override fun getItemCount(): Int = newOrdersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newOrder = newOrdersList[position],callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return ViewHolder(root)
    }
}