package com.example.tugas5retrofit.api

import com.example.tugas5retrofit.model.LoginRequest
import com.example.tugas5retrofit.model.LoginResponse
import com.example.tugas5retrofit.model.PasienResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("pasien")
    suspend fun getPasien(
        @Header("Authorization") token: String
    ): Response<PasienResponse>
}