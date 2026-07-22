package com.example.william_rodriguez_ap2_p2.domain.gasto.repository

import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import kotlinx.coroutines.flow.Flow

interface GastoRepository {
    fun getGastos(): Flow<Resource<List<Gasto>>>
    fun getGasto(id: Int): Flow<Resource<Gasto>>
    fun saveGasto(gasto: Gasto): Flow<Resource<Unit>>
}