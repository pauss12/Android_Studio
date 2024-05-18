package com.example.practicaandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.ActivityLoginBinding
import com.example.practicaandroid.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity(), OnClickListener {


    private lateinit var binding: ActivityLoginBinding
    private var usuario: Usuario? = null
    private lateinit var authFirebase: FirebaseAuth
    private var userAuth: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()

        if (intent.getSerializableExtra("usuario") != null) {
            usuario = intent.getSerializableExtra("usuario") as Usuario
            binding.editCorreo.setText(usuario!!.correo)
            binding.editPass.setText(usuario!!.pass)
        }

        binding.botonLogin.setOnClickListener(this)
        binding.botonSignUp.setOnClickListener(this)

    }

    fun instancias()
    {
        authFirebase = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            //LOGIN
            binding.botonLogin.id -> {
                if (!binding.editCorreo.text.toString().isEmpty() &&
                    !binding.editPass.text.toString().isEmpty()
                ) {

                    authFirebase.signInWithEmailAndPassword(
                        binding.editCorreo.text.toString(),
                        binding.editPass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra("correo", binding.editCorreo.text.toString())
                            intent.putExtra("uid", authFirebase.currentUser?.uid)
                            startActivity(intent)
                        } else {
                            Snackbar.make(binding.root, "Fallo en los datos", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

                    /*val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("correo", binding.editCorreo.text.toString())
                    startActivity(intent)*/

                }

            }

            //REGISTRO
            binding.botonSignUp.id->{

                //Me redirige a la pagina de registro
                val intent = Intent(applicationContext, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.help_menu, menu)

        return true
    }
}