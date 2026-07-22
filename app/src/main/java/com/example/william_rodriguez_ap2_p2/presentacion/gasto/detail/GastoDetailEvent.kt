package com.example.william_rodriguez_ap2_p2.presentacion.gasto.detail

sealed interface GastoDetailEvent {
    data class OnFechaChange(val fecha: String) : GastoDetailEvent
    data class OnSuplidorChange(val suplidor: String) : GastoDetailEvent
    data class OnNcfChange(val ncf: String) : GastoDetailEvent
    data class OnItbisChange(val itbis: String) : GastoDetailEvent
    data class OnMontoChange(val monto: String) : GastoDetailEvent
    data class LoadGasto(val id: Int) : GastoDetailEvent
    data object Save : GastoDetailEvent
}