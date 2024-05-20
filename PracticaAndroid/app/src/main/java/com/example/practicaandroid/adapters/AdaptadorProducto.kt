package com.example.practicaandroid.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.ActivityMainBinding
import com.example.practicaandroid.model.Producto
import com.example.practicaandroid.ui.DetallesProductoActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase


class AdaptadorProducto(var context: Context, private val uidUsuario: String) :  RecyclerView.Adapter<AdaptadorProducto.MyHolder>() {

    var lista: ArrayList<Producto> = ArrayList()

    class MyHolder(item: View) : RecyclerView.ViewHolder(item) {

        var imagen: ImageView = item.findViewById(R.id.imagenFila)
        var titulo: TextView = item.findViewById(R.id.tituloFila)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val vista: View = LayoutInflater.from(context).inflate(
            R.layout.item_modelo,
            parent, false
        )
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {

        return lista.size;
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        // asociar el holder (XML) con datos de la LISTA
        val elemento = lista[position]
        holder.titulo.text = elemento.name

        Glide.with(context).load(elemento.image).into(holder.imagen)

        //Controlar que pasa si le das a un elemento
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetallesProductoActivity::class.java)
            intent.putExtra("producto", elemento)
            context.startActivity(intent)
        }

        //Si le da al boton de "añadir al carrito", que lo meta dentro de la variable "recetas" del usuario que esta logeado
        holder.itemView.findViewById<Button>(R.id.addActividadCarrito).setOnClickListener {

            val referenciaUsuario = FirebaseDatabase.getInstance().getReference("usuarios").child(uidUsuario)

            referenciaUsuario.child("recetas").child(elemento.id.toString()).setValue(elemento)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Snackbar.make(holder.itemView, "Se ha subido a la BBDD", Snackbar.LENGTH_SHORT).show()

                    } else {
                        Snackbar.make(holder.itemView, "Ha ocurrido un error!", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }

    }

    fun addProducto(x: Producto, uidCurrentUser: String)
    {
        lista.add(x)
        notifyItemInserted(lista.size - 1)
    }

}