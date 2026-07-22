package com.example.william_rodriguez_ap2_p2.presentacion.navegation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object VacioList : Screen()
    @Serializable
    data class VacioDetail(val id: Int) : Screen()
}