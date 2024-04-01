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
    private var expression: StringBuilder = StringBuilder()

    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons = listOf(
            binding.botonAC, binding.botonUno, binding.botonDos, binding.botonTres,
            binding.botonCuatro, binding.botonCinco, binding.botonSeis, binding.botonSiete,
            binding.botonOcho, binding.botonNueve, binding.botonSuma, binding.botonResta,
            binding.botonMultiplicacion, binding.botonDivision, binding.botonComa, binding.botonIgual
        )
        for (button in buttons) {
            button.setOnClickListener(this)
        }

    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            /*Limpiamos la pantalla y todo lo que estuviera almacenado*/
            R.id.botonAC -> {
                expression.clear()
                firstNumber = 0.0
                secondNumber = 0.0
                binding.textoValor.text = ""
            }

            R.id.botonSuma -> {
                expression.append(firstNumber)
                expression.append("+")
                firstNumber = 0.0
                binding.textoValor.text = expression.toString()
            }
            R.id.botonResta -> {
                expression.append(firstNumber)  // Append first number to expression
                expression.append("-")
                // Reset firstNumber for next number
                firstNumber = 0.0
                binding.textoValor.text = expression.toString()
            }
            R.id.botonMultiplicacion -> {
                expression.append(firstNumber)  // Append first number to expression
                expression.append("*")
                // Reset firstNumber for next number
                firstNumber = 0.0
                binding.textoValor.text = expression.toString()
            }
            R.id.botonDivision -> {
                expression.append(firstNumber)  // Append first number to expression
                expression.append("/")
                // Reset firstNumber for next number
                firstNumber = 0.0
                binding.textoValor.text = expression.toString()
            }

            R.id.botonIgual -> {

                if (expression.isNotEmpty()) {

                    val expressionString = expression.toString()
                    val parts = expressionString.split("(?<=[-+*/])|(?=[-+*/])")
                    val primerNumero = parts[0].toDouble()
                    println("El valor de expressionString es $expressionString")
                    val operation = expressionString[expressionString.length - 1]
                    println("El valor de operacion es $operation --- el valor de numero 1 es $primerNumero y el valor del numero 2 es $firstNumber")


                }else {
                    Snackbar.make(
                        binding.root,
                        "ERROR: No hay suficientes operandos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            else -> {
                val buttonText = (v as Button).text.toString()
                if (v.id != R.id.botonIgual) {
                    // Append number to firstNumber before operation
                    firstNumber = firstNumber * 10 + buttonText.toDouble()
                }
                binding.textoValor.text = expression.toString() + firstNumber.toString()
            }

        }
    }
}
