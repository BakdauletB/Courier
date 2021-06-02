package kz.pillikan.courier.content.authorization.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.pillikan.courier.content.authorization.model.AuthorizationRepository
import kz.pillikan.courier.content.authorization.model.request.SignInRequest

class AuthorizationViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: AuthorizationRepository = AuthorizationRepository(application)

    val isError: MutableLiveData<String> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun logIn(signInRequest: SignInRequest) {
        viewModelScope.launch {
            try {
                val response = repository.signIn(signInRequest)
                isSuccess.postValue(response)
            } catch (e: Exception) {
                isError.postValue(e.toString())
            }
        }
    }
}