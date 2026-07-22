package com.example.william_rodriguez_ap2_p2.presentacion.gasto.list

import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto

data class GastoListUiState(
    val isLoading: Boolean = false,
    val gastos: List<Gasto> = emptyList(),
    val error: String? = null
)