package com.example.primerproyecto.Model

open class Usuario(var nombre: String? = null, var correo: String? = null, var pass: String? = null) {

    //lateinit var nombre: String
    //var correo: String? = null;
    var telefono: Int? = null;
    var direccion: String? = null;

    constructor(nombre: String,
                correo: String,
                pass:String,
                telefono: Int,
                direccion: String
    ): this(nombre, correo, pass)
    {
        this.telefono = telefono;
        this.direccion = direccion;
    }

    open fun mostrarDatos(): Unit{

        println("Nombre: $nombre")
        println("Numero de caracteres del nombre: ${nombre?.length ?: 0}")
    }

    open fun mostrarDatos(numero: Int): Unit{

        //for (i in 0 until numero){}

        (0..numero).forEach {

            mostrarDatos()
        }

        //(1..1000).random()

        when(numero)
        {
            0->{
                println("No se van a mostrar datos")
            }
            1->{
                mostrarDatos()
            }
        }
    }
}