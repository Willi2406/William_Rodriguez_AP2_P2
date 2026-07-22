package com.example.william_rodriguez_ap2_p2.domain.gasto.usecase

import javax.inject.Inject
import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import com.example.william_rodriguez_ap2_p2.domain.gasto.repository.GastoRepository
import kotlinx.coroutines.flow.Flow


class GetGastosUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(): Flow<Resource<List<Gasto>>> {
        return repository.getGastos()
    }
}


