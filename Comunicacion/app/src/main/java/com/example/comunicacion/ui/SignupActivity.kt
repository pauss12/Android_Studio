package com.example.comunicacion.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import com.example.comunicacion.databinding.ActivitySignupBinding
import com.example.comunicacion.model.Usuario

class SignupActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonRegistro.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){

            binding.botonRegistro.id->{

                //Los meto todos en una lista
                val editTexts = listOf(
                    binding.editNombre,
                    binding.editPassword,
                    binding.editPassword2,
                    binding.editCorreo
                )

                //Recorro la lista y voy comprobando si cada uno de ellos esta vacio o no
                for (editText in editTexts) {
                    if (editText.text.toString().isEmpty()) {
                        Snackbar.make(binding.root, "Hay campos vacíos", Snackbar.LENGTH_SHORT).show()
                        return
                    }
                }

                if (binding.editPassword.text.toString().equals(binding.editPassword2.text.toString())) {

                    //Sacar el dato que esta seleccionado en el spinner
                    //var perfil: String = binding.spinnerPerfil.adapter.getItem(binding.spinnerPerfil.selectedItemPosition).toString()

                    /*Otra forma* */

                    var perfil: String = binding.spinnerPerfil.selectedItem.toString()


                    var radioSeleccionado: RadioButton =
                        findViewById(binding.radioGroup.checkedRadioButtonId)
                    val genero = radioSeleccionado.text.toString();

                    val usuario =

                        Usuario(
                            binding.editNombre.text.toString(),
                            binding.editCorreo.text.toString(),
                            binding.editPassword.text.toString(),
                            genero,
                            perfil
                        )

                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    intent.putExtra("Usuario", usuario)
                    startActivity(intent)
                    finish()
                }
                Snackbar.make(binding.root, "Las contraseñas son distintas", Snackbar.LENGTH_SHORT).show()
                return
            }
        }
    }
}