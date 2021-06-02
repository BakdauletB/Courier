package kz.pillikan.courier.content.authorization.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignInTokenResponse(
    @SerializedName("userName")
    val userName: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null

):Serializable