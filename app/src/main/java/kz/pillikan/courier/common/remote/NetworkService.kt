package kz.pillikan.courier.common.remote

import kz.pillikan.courier.content.authorization.model.request.SignInRequest
import kz.pillikan.courier.content.authorization.model.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {
    @POST(EndPoints.POST_LOGIN)
    suspend fun signIn(
        @Header("clientId") clientId:String,
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>
}