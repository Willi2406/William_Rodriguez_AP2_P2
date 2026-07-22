package com.example.william_rodriguez_ap2_p2.data.remote.gasto.repository

import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.GastoRemoteDataSource
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto.GastoRequestDto
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import com.example.william_rodriguez_ap2_p2.domain.gasto.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val remoteDataSource: GastoRemoteDataSource
) : GastoRepository {

    override fun getGastos(): Flow<Resource<List<Gasto>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGastos()
        response.onSuccess { gastosDto ->
            emit(Resource.Success(gastosDto.map { it.toDomain() }))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener gastos"))
        }
    }

    override fun getGasto(id: Int): Flow<Resource<Gasto>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGasto(id)
        response.onSuccess { gastoDto ->
            emit(Resource.Success(gastoDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener el gasto"))
        }
    }

    override fun saveGasto(gasto: Gasto): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val requestDto = GastoRequestDto(
            fecha = gasto.fecha,
            suplidor = gasto.suplidor,
            ncf = gasto.ncf,
            itbis = gasto.itbis,
            monto = gasto.monto
        )

        val response = if (gasto.gastoId == 0) {
            remoteDataSource.createGasto(requestDto).map { Unit }
        } else {
            remoteDataSource.updateGasto(gasto.gastoId, requestDto)
        }

        response.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error al guardar el gasto"))
        }
    }
}