package ar.edu.unicen.seminario.kotlin

import java.util.ArrayList

fun main(){
    //las variables se declaran como val o var, seguido del nombre de la variable, dos puntos, el tipo de
    // la variable, igual, y el valor que le quiero asignar a esa variable.
    //por ejemplo
    //val/var name: Type = value (val es una constante, final, por defecto se usa este)
    //(var se usa cuando va a cambiar el valor, pero por defecto se usa val)
    //Se puede agregar el const adelante del val para que lo reemplace
    //en tiempo de compilación en lugar de en tiempo de ejecución.
    // Int Long Float Double Boolean Char String
    //? -> nullable
    //Any (no tiene tipo definido), Unit (void), Nothing(nunca se va a asginar, nunca va a retornar)
    //listOf, setOf, mapOf, mutableListOf, mutableSetOf, mutableMapOf
    //Clase Pair y Triple, Pair<T, U>, Triple<T, U, V>
    //Las funciones se declaran como fun, seguido del nombre de la función, paréntesis
    // fun nombreFuncion(nombreParametro: Tipo): TipoRetorno {}

    val name: String = "Agustin"

    val lista = ArrayList<Int>()
    lista.add(1)
    lista.add(2)
    lista.add(3)
    lista.add(4)

    val lista2 = listOf(5, 6, 7, 8) //no se puede hacer add, tengo que hacer una mutableListOf

    val map = mutableMapOf("a" to 1, "b" to 2)
    map["c"] = 3

    val par = Pair("Melisa", 32)

    var valor = 2

    when {
        valor ==1 -> println("Es 1")
        valor ==2 -> println("Es 2")
        valor ==3 -> println("Es 3")
        else -> println("No es 1 ni 2 ni 3")
    }

    when (valor){
        1 -> println("Es 1")
        2 -> println("Es 2")
        3 -> println("Es 3")
        else -> println("No es 1 ni 2 ni 3")
    }

    if (valor > 1){
        while (valor < 8){
            println("Valor: $valor")
            valor += 1
        }
    }

    for(i in 1..10){
        println("El valor de 1 es $i")
    }

    for(i in 1 until 10){
        println("El valor de 1 es $i")
    }

    println("Hello $name")
    println(lista)
    println(lista2)
    println(map)
    println(par)

    var resultado = sumar(3, 2)
    println("resultado sumar: " + resultado)

    resultado = restar(valor2 = 2, valor1 = 3)
    println("resultado restar: " + resultado)

    resultado = restar(valor1 = 2)
    println("resultado restar con un solo parámetro: " + resultado)

    val conjunto = setOf(1, 2, 3, 4, 5)

    val resultado2 = conjunto.getPromedio()

    println("El promedio es: $resultado2")

    val persona = Persona("Agustin", "Molina", 33)
    println("Nombre: ${persona.nombre}")
    println("Apellido: ${persona.apellido}")
    println("Edad: ${persona.edad}")
    println("Nombre completo: ${persona.getNombreCompleto()}")

    persona.edad = -1  //Esto va a devolver el error, porque la edad no puede ser negativa

    println("Edad: ${persona.edad}")
    println("Falta para jubilarse: ${persona.faltaParaJubilarse}")

    val trabajador = Trabajador("Agustin", "Molina", 33, "Programador")
    println("Nombre completo: ${trabajador.getNombreCompleto()}")
    println("Trabajo: ${trabajador.trabajo}")

}

fun sumar(a: Int, b: Int): Int {
    return a + b
}

fun restar(valor1: Int, valor2: Int = 0): Int{
    return valor1 - valor2
}

fun Set<Int>.getPromedio(): Double {
    var suma = 0.0
    for (element in this) {
        suma += element
    }
    return suma.toDouble() / this.size
}


//decalrion de clases, herencias, interfaces, enum y "siller"? class
//data class nos define automáticamente el equals, toString, hashCode y copy, el == es el equals