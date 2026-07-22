package com.example.william_rodriguez_ap2_p2.presentacion.vacio.list

data class VacioListUiState(
    val isLoading: Boolean = false,
    val vacios: List<String> = emptyList(),
    val error: String? = null
)
