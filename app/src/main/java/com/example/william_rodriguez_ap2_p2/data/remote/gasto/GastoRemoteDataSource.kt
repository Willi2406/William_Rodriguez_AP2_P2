package com.example.william_rodriguez_ap2_p2.data.remote.gasto

import com.example.william_rodriguez_ap2_p2.data.remote.GastoApi
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto.GastoDto
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto.GastoRequestDto
import retrofit2.HttpException
import javax.inject.Inject

class GastoRemoteDataSource @Inject constructor(
    private val api: GastoApi
) {
    suspend fun getGastos(): Result<List<GastoDto>> {
        return try {
            val response = api.getGastos()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido, ${e.localizedMessage}"))
        }
    }

    suspend fun getGasto(id: Int): Result<GastoDto> {
        return try {
            val response = api.getGasto(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido, ${e.localizedMessage}"))
        }
    }

    suspend fun createGasto(gasto: GastoRequestDto): Result<GastoDto> {
        return try {
            val response = api.createGasto(gasto)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido, ${e.localizedMessage}"))
        }
    }

    suspend fun updateGasto(id: Int, gasto: GastoRequestDto): Result<Unit> {
        return try {
            val response = api.updateGasto(id, gasto)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido, ${e.localizedMessage}"))
        }
    }
}