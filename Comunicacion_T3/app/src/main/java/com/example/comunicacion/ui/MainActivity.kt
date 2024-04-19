package com.example.comunicacion.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.comunicacion.adapters.AdaptadorProducto
import com.example.comunicacion.data.DataSet
import com.example.comunicacion.databinding.ActivityMainBinding
import com.example.comunicacion.model.Marca
import com.example.comunicacion.model.Producto
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorProducto: AdaptadorProducto
    private lateinit var uidCurrentUser: String
    private lateinit var nombre: String
    private lateinit var adapterSpinner: ArrayAdapter<Marca>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instacias()

        peticionJSON()

        persoAdaptadores()

        this.nombre = intent.getStringExtra("correo")!!
        this.uidCurrentUser = intent.getStringExtra("uid")!!
        binding.textoSaludo.text = nombre
        acciones()

    }

    private fun peticionJSON() {

        //Crear la peticion
        val url = "https://dummyjson.com/products"

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
    }

    fun acciones() {
        binding.spinnerSeleccion.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selecccion: Marca = parent!!.adapter.getItem(position) as Marca

                Snackbar.make(
                    binding.root,
                    "${selecccion.nombre} ${selecccion.calidad}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun instacias() {
        // crea la parte de datos del spinner
        adapterSpinner = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            DataSet.getAllMarcas()
        )

        adaptadorProducto = AdaptadorProducto(this)
    }


    fun persoAdaptadores() {

        // junta parte grafica con parte logica
        binding.spinnerSeleccion.adapter = adapterSpinner;
        // muestra el desplegable de forma visible
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.recyclerModelos.adapter = adaptadorProducto
        binding.recyclerModelos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                GridLayoutManager(this, 2)
        }

    }

}