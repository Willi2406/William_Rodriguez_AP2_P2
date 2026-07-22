package com.example.william_rodriguez_ap2_p2.presentacion.gasto.list

sealed interface GastoListEvent {
    data object Refresh : GastoListEvent
}