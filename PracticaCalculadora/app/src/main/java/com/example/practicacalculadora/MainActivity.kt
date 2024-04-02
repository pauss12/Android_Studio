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

    //Booleana para saber si se ha introducido el primer numero
    private var primerNumeroIntroducido: Boolean = false

    private var firstNumber: Double = 0.0
    private var firstNumberFinal: Double = 0.0
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

    fun formatearNumero(num: Double): String {
        return if (num == num.toInt().toDouble()) {
            // Si el número es un entero, muestra el número sin decimales
            num.toInt().toString()
        } else {
            // Si el número es decimal, muestra el número con decimales
            num.toString()
        }
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            /*Limpiamos la pantalla y todo lo que estuviera almacenado*/
            R.id.botonAC -> {
                expression.clear()
                firstNumber = 0.0
                secondNumber = 0.0
                primerNumeroIntroducido = false
                binding.textoValor.text = ""
            }

            //BOTON COMA -----------------------------
            R.id.botonComa -> {

                expression.append(firstNumber)
                expression.append(",")

                firstNumber = 0.0
                primerNumeroIntroducido = true
                binding.textoValor.text = expression.toString()
            }

            R.id.botonSuma -> {
                expression.append(firstNumber)
                expression.append("+")
                firstNumber = 0.0
                primerNumeroIntroducido = true
                binding.textoValor.text = expression.toString()
            }

            R.id.botonResta -> {

                expression.append(firstNumber)
                expression.append("-")

                firstNumber = 0.0
                primerNumeroIntroducido = true
                binding.textoValor.text = expression.toString()
            }

            R.id.botonMultiplicacion -> {
                expression.append(firstNumber)  // Append first number to expression
                expression.append("*")

                firstNumber = 0.0
                primerNumeroIntroducido = true
                binding.textoValor.text = expression.toString()
            }
            R.id.botonDivision -> {
                expression.append(firstNumber)
                expression.append("/")

                firstNumber = 0.0

                //Activar booleana de que se ha introducido el primer numero
                primerNumeroIntroducido = true

                binding.textoValor.text = expression.toString()
            }

            R.id.botonIgual -> {

                if (expression.isNotEmpty()) {

                    val expressionString = expression.toString()
                    val parts = expressionString.split("(?<=[-+*/])|(?=[-+*/])")
                    println("El valor de expressionString es $expressionString")
                    val operation = expressionString[expressionString.length - 1]
                    println("El valor de operacion es $operation --- el valor de numero 1 es $firstNumberFinal y el valor del numero 2 es $secondNumber")

                    var result: Double = 0.0

                    when (operation) {
                        '+' -> result = firstNumberFinal + secondNumber
                        '-' -> result = firstNumberFinal - secondNumber
                        '*' -> result = firstNumberFinal * secondNumber
                        '/' -> {
                            if (secondNumber != 0.0) {
                                result = firstNumberFinal / secondNumber
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "ERROR: División por cero",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }

                        else -> {

                            result = 0.0
                        }
                    }

                    binding.textoValor.text = "$expressionString $secondNumber = $result"

                    expression.clear()
                    firstNumberFinal = 0.0
                    secondNumber = 0.0
                    primerNumeroIntroducido = false

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

                    firstNumber = firstNumber * 10 + buttonText.toDouble()
                    if (!primerNumeroIntroducido)
                    {
                        firstNumberFinal = firstNumber
                    }else
                        secondNumber = firstNumber
                }

                binding.textoValor.text = expression.toString() + firstNumber.toString()
            }

        }
    }
}
