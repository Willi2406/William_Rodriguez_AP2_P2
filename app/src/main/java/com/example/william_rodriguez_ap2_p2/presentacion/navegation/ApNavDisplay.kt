package com.example.william_rodriguez_ap2_p2.presentacion.navegation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.william_rodriguez_ap2_p2.presentacion.vacio.detail.VacioDetailScreen
import com.example.william_rodriguez_ap2_p2.presentacion.vacio.list.VacioListScreen

@Composable
fun ApNavDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
) {
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(innerPadding),
        entryProvider = entryProvider {
            entry<Screen.VacioList> {
                VacioListScreen(
                    onVacioClick = { vacioId ->
                        backStack.add(Screen.VacioDetail(vacioId))
                    }
                )
            }

            entry<Screen.VacioDetail> { key ->
                VacioDetailScreen(
                    vacioId = key.id,
                    onBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }
        }
    )
}
