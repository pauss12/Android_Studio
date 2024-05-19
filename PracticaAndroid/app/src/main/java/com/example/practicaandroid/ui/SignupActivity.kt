package com.example.practicaandroid.ui

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivitySignupBinding
import com.example.practicaandroid.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity: AppCompatActivity() {


    private lateinit var authFirebase: FirebaseAuth
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()

        binding.botonRegistro.setOnClickListener {

            if (!binding.editCorreo.text.toString().isEmpty()
                && !binding.editNombre.text.toString().isEmpty()
                && !binding.editPass.text.toString().isEmpty()
                && !binding.editPass2.text.toString().isEmpty()
                && (binding.editPass.text.toString()
                    .equals(binding.editPass2.text.toString()))
            ) {

                // si todos los datos estan completos -> paso de pantalla
                val perfil: String = binding.spinnerPerfil.selectedItem.toString()

                //Comprobar si ha seleccionado alguna opcion
                val radioButtonId = binding.radioGroup.checkedRadioButtonId
                if (radioButtonId == -1) {
                    Snackbar.make(binding.root, "Debes seleccionar un género", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val radioSeleccionado: RadioButton =
                    findViewById(binding.radioGroup.checkedRadioButtonId)

                val genero = radioSeleccionado.text.toString();

                val usuario =
                    Usuario(
                        nombre = binding.editNombre.text.toString(),
                        correo = binding.editCorreo.text.toString(),
                        genero = genero,
                        perfil = perfil,
                        recetas = mutableMapOf()
                    )

                authFirebase.createUserWithEmailAndPassword(
                    usuario.correo!!,
                    binding.editPass.text.toString()
                )
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            firebaseDatabase.reference.child("usuarios")
                                .child(authFirebase.currentUser!!.uid).setValue(usuario)
                            Snackbar.make(
                                binding.root,
                                "Usuario registrado con exito",
                                Snackbar.LENGTH_SHORT
                            )
                                .setAction("ir a login") {

                                    val intent = Intent(this, LoginActivity::class.java)
                                    intent.putExtra("usuario", usuario)
                                    startActivity(intent)
                                }
                                .show()

                        } else {
                            Snackbar.make(binding.root,"Fallo en el proceso", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

            } else {
                // si todos los datos estan completos pero las pass no coincida -> aviso
                // los datos no estan completos pero las pass no coincida -> aviso
                Snackbar.make(binding.root, "Fallo en el proceso", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun instancias() {
        authFirebase = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance("https://practicaandroid-e9d77-default-rtdb.firebaseio.com/")
    }


}