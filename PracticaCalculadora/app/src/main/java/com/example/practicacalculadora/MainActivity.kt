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

    //Tiene lo que se va a ir mostrando en cada momento
    private var buffer: StringBuilder = StringBuilder()

    //Booleana para saber si se ha introducido el primer numero
    private var primerNumeroIntroducido: Boolean = false

    private var firstNumber: Double = 0.0
    private lateinit var operacion: String
    private var secondNumber: Double = 0.0

    private var numero: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons = listOf(
            binding.botonAC, binding.botonUno, binding.botonDos, binding.botonTres,
            binding.botonCuatro, binding.botonCinco, binding.botonSeis, binding.botonSiete,
            binding.botonOcho, binding.botonNueve, binding.botonSuma, binding.botonResta,
            binding.botonMultiplicacion, binding.botonDivision, binding.botonPunto, binding.botonIgual
        )
        for (button in buttons) {
            button.setOnClickListener(this)
        }

    }

    override fun onClick(v: View?) {

        println(primerNumeroIntroducido)

        when (v!!.id) {

            R.id.botonAC -> {

                firstNumber = 0.0
                secondNumber = 0.0
                operacion = ""
                binding.textoValor.text = ""

            }

            //BOTON COMA -----------------------------
            R.id.botonPunto -> {
                operacion = "."
                if (!buffer.toString().contains(".")) {
                    buffer.append(".")
                }
            }

            //BOTON SUMA ---------------------------
            R.id.botonSuma -> {
                operacion = "+"
                primerNumeroIntroducido = true
                buffer.clear()
            }

            //BOTON RESTA ---------------------------
            R.id.botonResta -> {
                operacion = "-"
                primerNumeroIntroducido = true
            }

            //BOTON MULTIPLICACION -----------------
            R.id.botonMultiplicacion -> {
                operacion = "*"
                primerNumeroIntroducido = true
            }

            //BOTON  DIVISION-----------------
            R.id.botonDivision -> {
                operacion = "/"
                primerNumeroIntroducido = true
            }


            else -> {

                if (!primerNumeroIntroducido)
                {
                    val numberString = (v as Button).text.toString()

                    //println("EL valor de numberString es $numberString")
                    buffer.append(numberString)
                    firstNumber = buffer.toString().toDouble()
                    println("El valor de buffer es $firstNumber")

                }
                else{

                    val numberString = (v as Button).text.toString()

                    //println("EL valor de numberString es $numberString")
                    buffer.append(numberString)
                    secondNumber = buffer.toString().toDouble()

                }

                println("El valor de buffer 1 es $firstNumber")
                println("El valor de buffer 2 es $secondNumber")
                binding.textoValor.text = buffer.toString()


            }
        }
    }


}
