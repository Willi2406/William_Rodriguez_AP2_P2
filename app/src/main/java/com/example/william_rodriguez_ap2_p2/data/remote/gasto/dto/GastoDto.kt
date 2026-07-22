package com.example.william_rodriguez_ap2_p2.data.remote.gasto.dto

import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto

data class GastoDto(
    val gastoId: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String?,
    val itbis: Double,
    val monto: Double
) {
    fun toDomain() = Gasto(
        gastoId = gastoId,
        fecha = fecha,
        suplidor = suplidor,
        ncf = ncf ?: "",
        itbis = itbis,
        monto = monto
    )
}

data class GastoRequestDto(
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double
)