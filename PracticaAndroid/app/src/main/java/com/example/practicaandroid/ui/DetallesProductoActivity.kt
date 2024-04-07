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

        if (producto != null) {

            binding.titulo.text = producto.name
            Glide.with(this).load(producto.image).into(binding.imagen)

        } else {

        }
    }
}
