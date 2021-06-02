package kz.pillikan.courier.content.authorization.model

import android.app.Application
import android.content.Context
import kz.pillikan.courier.common.preference.SessionManager
import kz.pillikan.courier.common.remote.ApiConstants
import kz.pillikan.courier.common.remote.Networking
import kz.pillikan.courier.content.authorization.model.request.SignInRequest
import kz.pillikan.courier.content.authorization.model.response.SignInTokenResponse

class AuthorizationRepository(application: Application) {

    private val networkService = Networking.create(ApiConstants.APP_BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("sessionManager", Context.MODE_PRIVATE)
    private var sessionManager: SessionManager = SessionManager(sharedPreferences)

    suspend fun signIn(signInRequest: SignInRequest): Boolean {
        val response = networkService.signIn(ApiConstants.CLIENT_ID, signInRequest)
        return when (response.code() == ApiConstants.RESPONSE_SUCCESS_CODE) {
            true -> {
                saveUser(response.body()!!.token)
                true
            }
            false -> {
                sessionManager.clear()
                false
            }
        }
    }

    private fun saveUser(signInTokenResponse: SignInTokenResponse) {
        signInTokenResponse.refreshToken?.let { sessionManager.setRefreshToken(it) }
        signInTokenResponse.accessToken?.let { sessionManager.setAccessToken(it) }
        signInTokenResponse.password?.let { sessionManager.setPassword(it) }
        signInTokenResponse.userName?.let { sessionManager.setUserName(it) }
        sessionManager.setIsAuthorize(true)
    }
}