package com.example.william_rodriguez_ap2_p2.domain.gasto.usecase

import android.R.attr.id
import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import com.example.william_rodriguez_ap2_p2.domain.gasto.repository.GastoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetGastoDetailUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Gasto>> {
        return repository.getGasto(id)
    }
}
