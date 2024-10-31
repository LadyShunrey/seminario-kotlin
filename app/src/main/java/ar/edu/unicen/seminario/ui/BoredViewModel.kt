package ar.edu.unicen.seminario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.data.BoredRepository
import ar.edu.unicen.seminario.ddl.models.ActivityRecommendation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoredViewModel @Inject constructor(
    private val boredRepository: BoredRepository
    ): ViewModel() {

        private val _loading = MutableStateFlow(false)
        val loading = _loading.asStateFlow()

        private val _error = MutableStateFlow(false)
        val error = _error.asStateFlow()

        private val _recommendation = MutableStateFlow<ActivityRecommendation?>(null)
        val recommendation = _recommendation.asStateFlow()

        fun getRecommendation(
            participants: Int
        ){
            viewModelScope.launch {
                _loading.value = true
                _error.value = false
                _recommendation.value = null

                val recommendation = boredRepository.getRecommendation(participants)

                _loading.value = false
                _recommendation.value = recommendation
                _error.value = recommendation == null
            }
        }
}