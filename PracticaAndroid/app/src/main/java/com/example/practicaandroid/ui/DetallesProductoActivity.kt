package com.example.practicaandroid.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practicaandroid.databinding.ActivityDetallesProductoBinding
import com.example.practicaandroid.model.Producto

class DetallesProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesProductoBinding
    private var i: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val producto = intent.getSerializableExtra("producto") as Producto

        binding.titulo.text = producto.name

        val ingredientList = SpannableStringBuilder("Ingredientes:\n\n")
        ingredientList.setSpan(StyleSpan(Typeface.BOLD), 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        for (ingredient in producto.ingredients) {
            ingredientList.append(" • $ingredient\n")  // Add bullet point and newline
        }

        binding.textoIngredientes.text = ingredientList

        val listaPasos = SpannableStringBuilder("Pasos:\n\n")
        listaPasos.setSpan(StyleSpan(Typeface.BOLD), 0, 7  , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        for (paso in producto.instructions) {
            listaPasos.append(" $i. $paso\n\n")
            i++;
        }

        binding.textoPasos.text = listaPasos

        Glide.with(this).load(producto.image).into(binding.imagen)

    }
}
