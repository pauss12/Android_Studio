package com.example.practicaandroid.model

import java.io.Serializable

class Usuario(
    var nombre: String?=null,
    var correo: String?=null,
    var pass: String?=null,
    var genero: String?=null,
    var perfil: String?=null,
    var recetas: Map<String, Producto>?

) : Serializable {
}