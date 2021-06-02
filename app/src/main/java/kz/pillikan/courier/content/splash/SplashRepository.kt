package kz.pillikan.courier.content.splash

import android.app.Application
import android.content.Context
import android.util.Log
import kz.pillikan.courier.common.helpers.NetworkHelpers
import kz.pillikan.courier.common.preference.SessionManager
import java.lang.Exception

class SplashRepository(application: Application) {

    companion object {
        const val TAG = "SplashRepository"
    }

    private var sharedPreferences =
        application.getSharedPreferences("sessionManager", Context.MODE_PRIVATE)
    private var sessionManager: SessionManager = SessionManager(sharedPreferences)

    fun checkNetwork(context: Context): Boolean {
        return try {
            NetworkHelpers.isNetworkConnected(context)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
            false
        }
    }

    fun checkAuthorize(): Boolean? {
        return try {
            sessionManager.getIsAuthorize()
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
            false
        }
    }

}