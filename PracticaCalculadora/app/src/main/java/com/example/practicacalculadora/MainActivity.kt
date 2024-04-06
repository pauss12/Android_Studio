package com.example.practicacalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.practicacalculadora.databinding.ActivityMainBinding
import java.lang.StringBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var historial: StringBuilder = StringBuilder()

    private lateinit var operacion: String

    private var numero: Double = 0.0

    //Botones para saber que simbolos se han pulsado
    private var botonMultiplicacion: Boolean = false
    private var botonDivision: Boolean = false
    private var botonResta: Boolean = false
    private var botonSuma: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons = listOf(
            binding.botonAC, binding.botonCero, binding.botonUno, binding.botonDos, binding.botonTres,
            binding.botonCuatro, binding.botonCinco, binding.botonSeis, binding.botonSiete,
            binding.botonOcho, binding.botonNueve, binding.botonSuma, binding.botonResta,
            binding.botonMultiplicacion, binding.botonDivision, binding.botonPunto, binding.botonIgual
        )
        for (button in buttons) {
            button.setOnClickListener(this)
        }

    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.botonAC -> {

                numero = 0.0

                operacion = ""
                binding.textoValor.text = ""

                binding.textoValorGuardado.text = ""

                historial.clear()

                botonDivision = false
                botonResta = false
                botonMultiplicacion = false
                botonSuma = false

            }

            //BOTON COMA -----------------------------
            R.id.botonPunto -> {
                operacion = "."
                if (!historial.toString().contains(".")) {
                    historial.append(" . ")
                }
            }

            //BOTON SUMA ---------------------------
            R.id.botonSuma -> {

                binding.textoValorGuardado.append(" + ")

                historial.append("+")

                operacion = "+"

                botonSuma = true

                numero = 0.0
            }

            //BOTON RESTA ---------------------------
            R.id.botonResta -> {

                operacion = "-"

                binding.textoValorGuardado.append(" - ")

                historial.append("-")

                botonResta = true

                numero = 0.0
            }

            //BOTON MULTIPLICACION -----------------
            R.id.botonMultiplicacion -> {

                operacion = "*"

                binding.textoValorGuardado.append(" * ")

                historial.append(" * ")

                botonMultiplicacion = true

                numero = 0.0
            }

            //BOTON  DIVISION-----------------
            R.id.botonDivision -> {

                operacion = "/"

                binding.textoValorGuardado.append(" / ")

                historial.append(" / ")

                botonDivision = true

                numero = 0.0
            }

            R.id.botonIgual -> {

                if (historial.isNotEmpty()) {

                    var result: Double = 0.0

                    println("El valor del historial es $historial")

                    numero = 0.0

                }

            }

            else -> {

                val numberString = (v as Button).text.toString()

                historial.append(numberString)

                binding.textoValorGuardado.text = historial

                //binding.textoValor.text = numero.toString()

            }
        }
    }


}
