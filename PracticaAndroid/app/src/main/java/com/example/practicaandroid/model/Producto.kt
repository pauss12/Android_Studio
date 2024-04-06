package com.example.practicaandroid.model

/*class Producto(var id: Int,
               var title: String,
               var descripcion: String,
               var thumbnail: String,
               var category: String,
               var price: Double)
{

}*/



class Producto(var id: Int,
               var name: String,
               var ingredients: List<String>,
               var instructions: List<String>,
               var thumbnail: String,
               var rating: Double)
{

}
