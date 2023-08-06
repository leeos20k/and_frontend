package com.example.frontend

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/api/expost") // 실제 API 엔드포인트로 변경해야 합니다.
    suspend fun signupUser(
        @Field("id") id: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone: String
    ): Response<ApiResponseModel>
}