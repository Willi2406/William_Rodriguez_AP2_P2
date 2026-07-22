package com.example.william_rodriguez_ap2_p2.presentacion.vacio.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VacioDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(VacioDetailUiState())
    val uiState = _uiState.asStateFlow()
}
