package com.example.william_rodriguez_ap2_p2.domain.gasto.model


data class Gasto(

    val gastoId: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double

)