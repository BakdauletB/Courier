package kz.pillikan.courier.content.orders.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.pillikan.courier.content.orders.model.repository.OrdersRepository
import kz.pillikan.courier.content.orders.model.response.order.OrdersResponse

class OrdersViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "OrdersViewModel"
    }

    private val repository = OrdersRepository(application)
    val newOrdersList = MutableLiveData<ArrayList<OrdersResponse>>()
    val currentOrdersList = MutableLiveData<ArrayList<OrdersResponse>>()

    fun getCurrentOrdersList() {
        currentOrdersList.postValue(repository.getCurrentOrdersList())
    }

    fun getNewOrdersList() {
        newOrdersList.postValue(repository.getNewOrdersList())
    }

}