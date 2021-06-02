package kz.pillikan.courier.content.orders.model.repository

import android.app.Application
import kz.pillikan.courier.content.orders.model.response.arrived.ArrivedResponse

class ArrivedRepository(application: Application) {
    suspend fun getArrivedList(): ArrayList<ArrivedResponse> {
        val arrivedList: ArrayList<ArrivedResponse> = arrayListOf()
        arrivedList.add(ArrivedResponse("Пицца Аман ЗБ","1x 5 000 тг"))
        arrivedList.add(ArrivedResponse("Бургер Аман Работай","2x 2 530 тг"))
        arrivedList.add(ArrivedResponse("Аман Лапша по-китайски","1x 930 тг"))
        return arrivedList
    }

}