package kz.pillikan.courier.content.profile.model.repository

import android.app.Application
import kz.pillikan.courier.content.profile.model.HistoryOfOrdersResponse

class EarningRepository(application: Application) {

    fun getHistoryOfOrders(): ArrayList<HistoryOfOrdersResponse> {
        val historyOfOrdersResponse: ArrayList<HistoryOfOrdersResponse> = ArrayList()
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        historyOfOrdersResponse.add(
            HistoryOfOrdersResponse(
                number = 7408,
                retail = "Coffee Boom"
            )
        )
        return historyOfOrdersResponse
    }
}