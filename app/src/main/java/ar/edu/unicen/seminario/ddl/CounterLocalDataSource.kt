package ar.edu.unicen.seminario.ddl

import android.content.SharedPreferences
import ar.edu.unicen.seminario.di.CounterSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CounterLocalDataSource @Inject constructor(
    @CounterSharedPreference
    private val sharedPreferences: SharedPreferences
) {

    suspend fun getCounter(): Int {
        return withContext(Dispatchers.IO){
            sharedPreferences.getInt("counter", 0)
        }
    }

    suspend fun setCounter(value: Int): Boolean {
        return withContext(Dispatchers.IO){
            try{
                delay(2000)
                val editor = sharedPreferences.edit()
                editor.putInt("counter", value)
                editor.commit()
            }catch(e: Exception){
                e.printStackTrace()
                false
            }
        }
    }

    suspend fun reset(): Boolean {
        return withContext(Dispatchers.IO){
            try{
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.commit()
            }catch(e: Exception){
                e.printStackTrace()
                false
            }
        }
    }
}