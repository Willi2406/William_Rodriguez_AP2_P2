package com.example.william_rodriguez_ap2_p2.domain.gasto.usecase

import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import com.example.william_rodriguez_ap2_p2.domain.gasto.repository.GastoRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveGastoUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(gasto: Gasto): Flow<Resource<Unit>> {
        return repository.saveGasto(gasto)
    }
}