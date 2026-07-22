package com.example.william_rodriguez_ap2_p2.presentacion.gasto.detail

data class GastoDetailUiState(
    val gastoId: Int = 0,
    val fecha: String = "22/7/2026",
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: String = "0.0",
    val monto: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val suplidorError: String? = null,
    val montoError: String? = null,
    val isSuccess: Boolean = false
)