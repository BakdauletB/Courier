package kz.pillikan.courier.content.orders.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.pillikan.courier.content.orders.model.repository.ArrivedRepository
import kz.pillikan.courier.content.orders.model.response.arrived.ArrivedResponse

class ArrivedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ArrivedRepository(application)

    val isError = MutableLiveData<String>()
    val arrivedList = MutableLiveData<ArrayList<ArrivedResponse>>()

    suspend fun getArrivedList() {
        viewModelScope.launch {
            try {
                arrivedList.postValue(repository.getArrivedList())
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

}