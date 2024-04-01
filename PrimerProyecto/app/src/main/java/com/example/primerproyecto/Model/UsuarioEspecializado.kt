package com.example.primerproyecto.Model

class UsuarioEspecializado(nombre: String, correo: String, pass: String, var Salario: Int ):

    Usuario(nombre, correo, pass) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Salario: $Salario")
        val usuario = Usuario("", "", "")
    }
}