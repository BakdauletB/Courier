package kz.pillikan.courier.content.orders.model.repository

import android.app.Application
import kz.pillikan.courier.content.orders.model.response.order.OrdersResponse

class OrdersRepository(application: Application) {
    fun getCurrentOrdersList(): ArrayList<OrdersResponse> {
        val currentList: ArrayList<OrdersResponse> = arrayListOf()
        currentList.add(
            OrdersResponse(
                "Прибыть в заведение " +
                        "в течении 10 минут",
                "Срочный на 15:00",
                "7408",
                "Coffee Boom",
                "ул. Рыскулова, 84",
                "Время: 14:30"
            )
        )
        return currentList
    }

    fun getNewOrdersList(): ArrayList<OrdersResponse> {
        val newList: ArrayList<OrdersResponse> = arrayListOf()
        newList.add(
            OrdersResponse(
                "Прибыть в заведение " +
                        "в течении 10 минут",
                "Срочный на 15:00",
                "7408",
                "Coffee Boom",
                "ул. Рыскулова, 84",
                "Время: 14:30"
            )
        )
        newList.add(
            OrdersResponse(
                "Прибыть в заведение " +
                        "в течении 10 минут",
                "Срочный на 15:00",
                "7408",
                "Coffee Boom",
                "ул. Рыскулова, 84",
                "Время: 14:30"
            )
        )
        return newList
    }

}