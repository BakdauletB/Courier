package kz.pillikan.courier.content.authorization.model.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("token")
    val token: SignInTokenResponse
)