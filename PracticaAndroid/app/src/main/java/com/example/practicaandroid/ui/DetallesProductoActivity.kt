package com.example.practicaandroid.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practicaandroid.databinding.ActivityDetallesProductoBinding
import com.example.practicaandroid.model.Producto


class DetallesProductoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetallesProductoBinding
    private var i: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityDetallesProductoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val producto = intent.getSerializableExtra("producto") as Producto

        //COGER EL NOMBRE DE LA RECETA EN ESPECIFICO -------------------------------
        binding.titulo.text = producto.name

        //CARGAR EL RATING DE ESTA RECETA ------------------
        val rating = producto.rating.toString()
        val ratingLista = SpannableStringBuilder("THIS RECIPE HAS A $rating / 5 ")
        ratingLista.setSpan(StyleSpan(Typeface.BOLD), 17, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ratingLista.setSpan(RelativeSizeSpan(1.2f), 17, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.rating.text = ratingLista

        //MOSTRAR LA LISTA DE INGREDIENTES --------------------------------------------------
        val ingredientList = SpannableStringBuilder("Ingredientes:\n\n")

        //Poner el titulo de "Ingredientes" en negrita
        ingredientList.setSpan(StyleSpan(Typeface.BOLD), 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        for (ingredient in producto.ingredients!!) {
            ingredientList.append(" • $ingredient\n")
        }

        binding.textoIngredientes.text = ingredientList

        //MOSTRAR LA LISTA DE PASOS DE LAS INSTRUCCIONES  --------------------------------------
        val listaPasos = SpannableStringBuilder("Pasos:\n\n")

        //Poner el titulo de "Pasos" en negrita
        listaPasos.setSpan(StyleSpan(Typeface.BOLD), 0, 7  , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        for (paso in producto.instructions!!) {
            listaPasos.append(" $i. $paso\n\n")
            i++;
        }

        binding.textoPasos.text = listaPasos

        //CARGA LA IMAGEN -----------------------------------------------------------
        Glide.with(this).load(producto.image).into(binding.imagen)

    }
}
