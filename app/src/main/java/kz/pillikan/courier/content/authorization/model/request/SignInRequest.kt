package kz.pillikan.courier.content.authorization.model.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("userName")
    val userName: String?=null,
    @SerializedName("password")
    val password: String?=null
)