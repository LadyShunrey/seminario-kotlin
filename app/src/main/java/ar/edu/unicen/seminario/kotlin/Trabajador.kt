package ar.edu.unicen.seminario.kotlin

class Trabajador(
    nombre: String,
    apellido: String,
    edad: Int,
    var trabajo: String
) : Persona(nombre, apellido, edad) {


}