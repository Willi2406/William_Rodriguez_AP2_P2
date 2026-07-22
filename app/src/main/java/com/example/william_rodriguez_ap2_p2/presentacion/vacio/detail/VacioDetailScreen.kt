package com.example.william_rodriguez_ap2_p2.presentacion.vacio.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun VacioDetailScreen(
    vacioId: Int,
    onBack: () -> Unit
) {
    Text(text = "Vacio Detail Screen for $vacioId")
}
