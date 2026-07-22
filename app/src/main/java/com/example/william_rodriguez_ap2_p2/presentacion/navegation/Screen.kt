package com.example.william_rodriguez_ap2_p2.presentacion.navegation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object GastoList : Screen()

    @Serializable
    data class GastoDetail(val id: Int = 0) : Screen()
}