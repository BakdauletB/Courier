package kz.pillikan.courier.common.preference

import android.content.SharedPreferences

class SessionManager(private val pref: SharedPreferences) {

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val KEY_IS_AUTHORIZE = "KEY_IS_AUTHORIZE"
        const val KEY_USER_NAME="KEY_USER_NAME"
        const val KEY_PASSWORD="KEY_PASSWORD"
    }
    fun getPassword(): String? = pref.getString(KEY_PASSWORD, null)
    fun setPassword(phone: String) {
        pref.edit().putString(KEY_PASSWORD, phone).apply()
    }
    fun getUserName(): String? = pref.getString(KEY_USER_NAME, null)
    fun setUserName(iin: String) {
        pref.edit().putString(KEY_USER_NAME, iin).apply()
    }

    fun getAccessToken(): String? = pref.getString(ACCESS_TOKEN, null)
    fun setAccessToken(access_token: String) {
        pref.edit().putString(ACCESS_TOKEN, access_token).apply()
    }

    fun getRefreshToken(): String? = pref.getString(REFRESH_TOKEN, null)
    fun setRefreshToken(refresh_token: String) {
        pref.edit().putString(REFRESH_TOKEN, refresh_token).apply()
    }

    fun getIsAuthorize(): Boolean = pref.getBoolean(KEY_IS_AUTHORIZE, false)
    fun setIsAuthorize(isAuthorize: Boolean) {
        pref.edit().putBoolean(KEY_IS_AUTHORIZE, isAuthorize).apply()
    }

    fun clear() {
        pref.edit().clear().apply()
    }

}