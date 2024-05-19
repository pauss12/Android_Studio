package com.example.practicaandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.practicaandroid.R
import com.example.practicaandroid.adapters.AdaptadorProducto
import com.example.practicaandroid.databinding.ActivityMainBinding
import com.example.practicaandroid.model.Producto
import com.example.practicaandroid.model.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adaptadorProducto: AdaptadorProducto
    private lateinit var nombre: String

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var uidCurrentUser: String
    private lateinit var databaseReference: DatabaseReference

    // referencia -> nodo sobre el cual me puedo colocar y trabajar de ahi en adelante
    // child -> todos los nodos. Si hago referencia a un nodo que no existe, se crea automaticamente
    // value -> dato asociado a un nodo. Si el nodo existe y se le da un valor nuevo -> update

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        peticionFirebase()
        persoAdaptadores()
        peticionJSON()

        this.nombre = intent.getStringExtra("correo")!!
        binding.textoSaludo.text = "Bienvenido $nombre ;) "

        this.uidCurrentUser = intent.getStringExtra("uid")!!
        binding.textoSaludo.text = nombre

    }

    //PETICION FIREBASE ------------------------------------------------------------------------------
    private fun peticionFirebase() {

        firebaseDatabase.reference.child("recipes")
            .child("recipes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        it.child("name").value
                        it.child("ingredients").value
                        it.child("instructions").value
                        it.child("image").value
                        it.child("rating").value
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    //PETICION JSON ------------------------------------------------------------------------------------
    private fun peticionJSON() {

        val url = "https://dummyjson.com/recipes"

        val peticion: JsonObjectRequest = JsonObjectRequest(url, {

            val recetas: JSONArray = it.getJSONArray("recipes")

            for ( i in 0..< recetas.length())
            {
                val recipe: JSONObject = recetas.getJSONObject(i)
                val recipeOBJ: Producto = Gson().fromJson(recipe.toString(), Producto::class.java)

                adaptadorProducto.addProducto(recipeOBJ, this.uidCurrentUser)

                val productId = databaseReference.push().key
                if (productId != null) {
                    databaseReference.child(productId).setValue(recipeOBJ)
                }

                Log.v("dats", "${recipeOBJ.id} ${recipeOBJ.name}")
            }

        }, {
            Log.v("dats", "Error de conexion")
        })

        //Lanzarla
        Volley.newRequestQueue(applicationContext).add(peticion)
    }

    //INSTACIAS ----------------------------------------------------------------------------------------
    fun instancias() {

        adaptadorProducto = AdaptadorProducto(this, "")
        firebaseDatabase = FirebaseDatabase.getInstance("https://practicaandroid-e9d77-default-rtdb.firebaseio.com/")
        databaseReference = firebaseDatabase.reference.child("recipes")
    }


    //PERO ADAPTADORES -------------------------------------------------------------------------------
    fun persoAdaptadores() {

        binding.recyclerModelos.adapter = adaptadorProducto

        binding.recyclerModelos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    // FUNCIONES PARA EL MENU --------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_add_nodo -> {
                databaseReference = firebaseDatabase.reference.child("usuarios").child("uid")
                databaseReference
                    .child("usuario2").setValue("usuario1")
            }

            R.id.menu_del_nodo -> {
                firebaseDatabase.reference.child("usuarios")
                    .child("usuario2").setValue(null)
            }

            R.id.menu_get_nodo -> {
                // obtener informacion sobre el nodo con UUID EMQ2zlGUt8VMITrTDToBfOIdWuy2
                firebaseDatabase.reference.child("usuarios")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val iterable: Iterable<DataSnapshot> = snapshot.children
                            iterable.forEach {
                                val usuario: Usuario =
                                    Gson().fromJson(it.value.toString(), Usuario::class.java)
                                Log.v("datos", usuario.nombre.toString())
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })
            }
        }

        return true
    }

}