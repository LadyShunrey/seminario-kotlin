package ar.edu.unicen.seminario

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Counter (
    private var valor: Int = 0
): Parcelable{

    fun incrementar(){
        valor++
    }

    override fun toString(): String {
        return valor.toString()
    }
}