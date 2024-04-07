package com.example.practicaandroid.model

import java.io.Serializable

class Producto(var id: Int,
               var name: String,
               var ingredients: List<String>,
               var instructions: List<String>,
               var image: String,
               var rating: Double) : Serializable
{

}
