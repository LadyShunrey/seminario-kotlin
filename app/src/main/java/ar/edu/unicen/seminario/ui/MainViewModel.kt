package ar.edu.unicen.seminario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.CounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val counterRepository: CounterRepository
): ViewModel() {

//    private val _counter = MutableLiveData(savedStateHandle["counter"] ?: 0)
    private val _counter = MutableStateFlow(0)
//    val counter: StateFlow<Int> = savedStateHandle.getStateFlow("counter", 0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true
            val counterValue = counterRepository.getCounter()
            _counter.value = counterValue
            _loading.value = false
        }
    }

    fun increment(){
        viewModelScope.launch {
            _loading.value = true
            val newCounter = counter.value + 1
            val result = counterRepository.setCounter(newCounter)
            if(result){
                _counter.value = newCounter
            }
            _loading.value = false
        }

//        _counter.value = _counter.value?.plus(1)


//        viewModelScope.launch {
//            delay(2000)
//
////            Thread.sleep(2000) //simulo una operación costosa
//            savedStateHandle["counter"] = counter.value?.plus(1)
//
//        }

    }

    fun reset(){
        viewModelScope.launch {
            _loading.value = true
            val result = counterRepository.reset()
            if(result){
                _counter.value = 0
            }
            _loading.value = false
        }
    }
}