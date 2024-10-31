package ar.edu.unicen.seminario.ddl

import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val counterLocalDataSource: CounterLocalDataSource
) {

    suspend fun getCounter(): Int {
        return counterLocalDataSource.getCounter()
    }

    suspend fun setCounter(value: Int): Boolean {
        return counterLocalDataSource.setCounter(value)
    }

    suspend fun reset(): Boolean {
        return counterLocalDataSource.reset()
    }
}