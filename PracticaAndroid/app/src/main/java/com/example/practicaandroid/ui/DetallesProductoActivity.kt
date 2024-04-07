package com.example.practicaandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practicaandroid.databinding.ActivityDetallesProductoBinding
import com.example.practicaandroid.model.Producto

class DetallesProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val producto = intent.getSerializableExtra("producto") as Producto

        binding.titulo.text = producto.name

        val ingredientList = StringBuilder()
        ingredientList.append("Ingredientes: \n\n")
        for (ingredient in producto.ingredients) {
            ingredientList.append(" • $ingredient\n")  // Add bullet point and newline
        }

        binding.textoIngredientes.text = ingredientList.toString()

        val listaPasos = StringBuilder()
        listaPasos.append("Pasos: \n\n")
        for (paso in producto.instructions) {
            listaPasos.append(" • $paso\n")
        }

        binding.textoPasos.text = listaPasos.toString()

        Glide.with(this).load(producto.image).into(binding.imagen)

    }
}
