package com.example.william_rodriguez_ap2_p2.data.remote

import com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto.GastoDto
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto.GastoRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface GastoApi {
    @GET("api/Gastos")
    suspend fun getGastos(): Response<List<GastoDto>>

    @GET("api/Gastos/{id}")
    suspend fun getGasto(@Path("id") id: Int): Response<GastoDto>

    @POST("api/Gastos")
    suspend fun createGasto(@Body gasto: GastoRequestDto): Response<GastoDto>

    @PUT("api/Gastos/{id}")
    suspend fun updateGasto(@Path("id") id: Int, @Body gasto: GastoRequestDto): Response<Unit>
}