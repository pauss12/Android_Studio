package com.example.practicaandroid.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaandroid.R
import com.example.practicaandroid.model.Producto
import com.example.practicaandroid.ui.DetallesProductoActivity


class AdaptadorProducto(var context: Context) :  RecyclerView.Adapter<AdaptadorProducto.MyHolder>() {

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
    }

    fun addProducto(x: Producto)
    {
        lista.add(x)
        notifyItemInserted(lista.size - 1)
    }

}