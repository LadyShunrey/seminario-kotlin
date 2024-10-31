package ar.edu.unicen.seminario.kotlin

open class Persona (
    var nombre: String,
    var apellido: String,
    var edad: Int
){
    val faltaParaJubilarse: Int
        get() = 65 - edad

    init {
        println("Se está creando una persona")
    }

//    if (edad < 0) {
//        throw IllegalArgumentException("La edad no puede ser negativa")
//    }
//    set(value) {
//        if (value >= 0) {
//            field = value
//        } else {
//            throw IllegalArgumentException("La edad no puede ser negativa")
//        }
//    }

    constructor(nombre: String, apellido: String) : this(nombre, apellido, 3)

    fun getNombreCompleto(): String{
        return "$nombre $apellido"
    }
}