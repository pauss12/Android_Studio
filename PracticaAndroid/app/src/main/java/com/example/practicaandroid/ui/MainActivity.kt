package com.example.practicaandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.practicaandroid.adapters.AdaptadorProducto
import com.example.practicaandroid.databinding.ActivityMainBinding
import com.example.practicaandroid.model.Producto
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorProducto: AdaptadorProducto
    private lateinit var nombre: String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instacias()

        persoAdaptadores()

        peticionJSON()

        this.nombre = intent.getStringExtra("correo")!!
        binding.textoSaludo.text = nombre


    }

    /*private fun peticionJSON() {

        //Crear la peticion
        val url = "https://dummyjson.com/products"

        //https://dummyjson.com/recipes

        //No hay que ponerle el nombre de la funcion, por eso se ponen las llaves directamente
        val peticion: JsonObjectRequest = JsonObjectRequest(url, {

            val productos: JSONArray = it.getJSONArray("products")

            for ( i in 0..< productos.length())
            {
                val producto: JSONObject = productos.getJSONObject(i)

                val productoOBJ: Producto = Gson().fromJson(producto.toString(), Producto::class.java)

                adaptadorProducto.addProducto(productoOBJ)

                Log.v("dats", "${productoOBJ.id} ${productoOBJ.title}")
            }

        }, {
            Log.v("dats", "Error de conexion")
        })

        //Lanzarla
        Volley.newRequestQueue(applicationContext).add(peticion)
    }*/

    private fun peticionJSON() {

        //Crear la peticion
        val url = "https://dummyjson.com/recipes"

        //No hay que ponerle el nombre de la funcion, por eso se ponen las llaves directamente
        val peticion: JsonObjectRequest = JsonObjectRequest(url, {

            val recetas: JSONArray = it.getJSONArray("recipes")

            for ( i in 0..< recetas.length())
            {
                val recipe: JSONObject = recetas.getJSONObject(i)

                val recipeOBJ: Producto = Gson().fromJson(recipe.toString(), Producto::class.java)

                adaptadorProducto.addProducto(recipeOBJ)

                Log.v("dats", "${recipeOBJ.id} ${recipeOBJ.name}")
            }

        }, {
            Log.v("dats", "Error de conexion")
        })

        //Lanzarla
        Volley.newRequestQueue(applicationContext).add(peticion)
    }

    fun instacias() {

        adaptadorProducto = AdaptadorProducto(this)

    }


    fun persoAdaptadores() {

        binding.recyclerModelos.adapter = adaptadorProducto

        binding.recyclerModelos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }



}