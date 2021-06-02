package kz.pillikan.courier.content.profile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.pillikan.courier.content.profile.model.HistoryOfOrdersResponse
import kz.pillikan.courier.content.profile.model.repository.EarningRepository

class EarningsViewFragment (application: Application) : AndroidViewModel(application) {

    private val repository = EarningRepository(application)

    val historyOfOrdersResponse = MutableLiveData<List<HistoryOfOrdersResponse>>()

    fun getHistoryOfOrders() {
        historyOfOrdersResponse.postValue(repository.getHistoryOfOrders())
    }

}