package com.bluewave.modernapp.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluewave.modernapp.data.OneModelRepository
import com.bluewave.modernapp.ui.model.OneModelUiState.Success
import com.bluewave.modernapp.ui.model.OneModelUiState.Error
import com.bluewave.modernapp.ui.model.OneModelUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneModelViewModel @Inject constructor(
    private val repository: OneModelRepository
) : ViewModel() {

    fun addOneModel(name: String) {
        viewModelScope.launch {
            repository.add(name)
        }
    }

    val uiState: StateFlow<OneModelUiState> = repository
        .myItems.map<List<String>, OneModelUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading);
}


sealed interface OneModelUiState{
    object Loading : OneModelUiState
    data class Success(val data: List<String>) : OneModelUiState
    data class Error(val exception: Throwable?) : OneModelUiState
}