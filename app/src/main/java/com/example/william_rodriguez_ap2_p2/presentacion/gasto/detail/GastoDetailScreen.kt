package com.example.william_rodriguez_ap2_p2.presentacion.gasto.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoDetailScreen(
    gastoId: Int,
    viewModel: GastoDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(gastoId) {
        viewModel.onEvent(GastoDetailEvent.LoadGasto(gastoId))
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.gastoId == 0) "Registrar gasto" else "Modificar gasto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atras")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.error?.let {
                Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
            }

            OutlinedTextField(
                value = state.fecha,
                onValueChange = { viewModel.onEvent(GastoDetailEvent.OnFechaChange(it)) },
                label = { Text("Fecha") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.suplidor,
                onValueChange = { viewModel.onEvent(GastoDetailEvent.OnSuplidorChange(it)) },
                label = { Text("Suplidor") },
                isError = state.suplidorError != null,
                supportingText = { state.suplidorError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.ncf,
                onValueChange = { viewModel.onEvent(GastoDetailEvent.OnNcfChange(it)) },
                label = { Text("NCF") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(GastoDetailEvent.OnMontoChange(it)) },
                label = { Text("Monto") },
                isError = state.montoError != null,
                supportingText = { state.montoError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.onEvent(GastoDetailEvent.Save) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}