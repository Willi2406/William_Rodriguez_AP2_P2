package com.example.william_rodriguez_ap2_p2.presentacion.gasto.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.usecase.GetGastoDetailUseCase
import com.example.william_rodriguez_ap2_p2.domain.gasto.usecase.SaveGastoUseCase
import com.example.william_rodriguez_ap2_p2.domain.gasto.model.Gasto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoDetailViewModel @Inject constructor(
    private val getGastoUseCase: GetGastoDetailUseCase,
    private val saveGastoUseCase: SaveGastoUseCase,
    savedState: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GastoDetailUiState())
    val state = _state.asStateFlow()

    init {
    }

    fun onEvent(event: GastoDetailEvent) {
        when (event) {
            is GastoDetailEvent.OnFechaChange -> _state.update { it.copy(fecha = event.fecha) }
            is GastoDetailEvent.OnSuplidorChange -> _state.update { it.copy(suplidor = event.suplidor, suplidorError = null) }
            is GastoDetailEvent.OnNcfChange -> _state.update { it.copy(ncf = event.ncf) }
            is GastoDetailEvent.OnItbisChange -> _state.update { it.copy(itbis = event.itbis) }
            is GastoDetailEvent.OnMontoChange -> _state.update { it.copy(monto = event.monto, montoError = null) }
            is GastoDetailEvent.LoadGasto -> {
                if (event.id > 0) {
                    loadGasto(event.id)
                }
            }
            GastoDetailEvent.Save -> saveGasto()
        }
    }

    private fun loadGasto(id: Int) {
        viewModelScope.launch {
            getGastoUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        result.data?.let { gasto ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    gastoId = gasto.gastoId,
                                    fecha = gasto.fecha,
                                    suplidor = gasto.suplidor,
                                    ncf = gasto.ncf,
                                    itbis = gasto.itbis.toString(),
                                    monto = gasto.monto.toString()
                                )
                            }
                        }
                    }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    private fun saveGasto() {
        val currentState = _state.value
        var hasError = false

        if (currentState.suplidor.isBlank()) {
            _state.update { it.copy(suplidorError = "El suplidor es obligatorio") }
            hasError = true
        }

        val montoValue = currentState.monto.toDoubleOrNull() ?: 0.0
        if (montoValue <= 0.0) {
            _state.update { it.copy(montoError = "El monto debe ser mayor a 0") }
            hasError = true
        }

        if (hasError) return

        val gasto = Gasto(
            gastoId = currentState.gastoId,
            fecha = currentState.fecha,
            suplidor = currentState.suplidor,
            ncf = currentState.ncf,
            itbis = currentState.itbis.toDoubleOrNull() ?: 0.0,
            monto = montoValue
        )

        viewModelScope.launch {
            saveGastoUseCase(gasto).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, isSuccess = true) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }
}