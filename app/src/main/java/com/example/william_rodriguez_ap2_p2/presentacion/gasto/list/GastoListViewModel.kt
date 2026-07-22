package com.example.william_rodriguez_ap2_p2.presentacion.gasto.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.william_rodriguez_ap2_p2.data.remote.Resource
import com.example.william_rodriguez_ap2_p2.domain.gasto.usecase.GetGastosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class GastoListViewModel @Inject constructor(
    private val getGastosUseCase: GetGastosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GastoListUiState())
    val state = _state.asStateFlow()

    init {
        loadGastos()
    }

    fun onEvent(event: GastoListEvent) {
        when (event) {
            GastoListEvent.Refresh -> loadGastos()
        }
    }

    private fun loadGastos() {
        viewModelScope.launch {
            getGastosUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(isLoading = false, gastos = result.data ?: emptyList())
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}