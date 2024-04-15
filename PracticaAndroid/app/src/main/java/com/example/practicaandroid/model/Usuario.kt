package com.example.practicaandroid.model

import java.io.Serializable

class Usuario(
    var nombre: String,
    var correo: String,
    var pass: String,
    var genero: String,
) : Serializable {
}