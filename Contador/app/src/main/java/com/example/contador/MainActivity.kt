package com.example.contador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    //Técnica de binding
    private lateinit var binding: ActivityMainBinding
    //Los enteros no tienen lateinit
    private var contador: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hace que sea vista
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        contador = savedInstanceState?.getInt("contador") ?: 0
        binding.textoContador.text = contador.toString()
        binding.botonIncremento.setOnClickListener(this)
        binding.botonDecremento.setOnClickListener(this)
        binding.botonReset?.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("contador", contador)
    }

    override fun onClick(v: View?) {

        when(v!!.id){

            //Tambien se puede poner como "binding.botonIncremento.id"
            R.id.botonIncremento->{
                contador++;
            }

            R.id.botonDecremento->{
                contador--;
            }

            R.id.botonReset->{
                contador=0
            }

        }
        binding.textoContador.text = contador.toString()
    }
}