package kz.pillikan.courier.content.orders.model.response.order

import java.io.Serializable


data class OrdersResponse(
    val arrival: String? = null,
    val time: String? = null,
    val numberOrder: String? = null,
    val nameRetail: String? = null,
    val address: String? = null,
    val timeOrder: String? = null
):Serializable