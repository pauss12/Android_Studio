package com.example.practicaandroid.model

import java.io.Serializable

class Producto(
        var id: Int,
        var name: String,
        var ingredients: List<String>?=null,
        var instructions: List<String>?=null,
        var image: String?=null,
        var rating: Double?=null,
) : Serializable {

}
