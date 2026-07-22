package com.example.william_rodriguez_ap2_p2.presentacion.gasto.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoListScreen(
    viewModel: GastoListViewModel = hiltViewModel(),
    onAddGasto: () -> Unit,
    onGastoClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Registro de gastos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGasto) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar gasto")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error?.let {
                Text(
                    text = "Error: $it",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.gastos) { gasto ->
                    GastoItem(gasto = gasto, onClick = { onGastoClick(gasto.gastoId) })
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Registros: ${state.gastos.size}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Total: $${state.gastos.sumOf { it.monto }}", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun GastoItem(gasto: Gasto, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${gasto.gastoId} | Fecha: ${gasto.fecha.take(10)}", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Suplidor: ${gasto.suplidor}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Monto: ${gasto.monto}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}