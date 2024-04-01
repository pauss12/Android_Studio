package com.example.comunicacion.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.comunicacion.databinding.ActivityLoginBinding
import com.example.comunicacion.model.Usuario

class LoginActivity : AppCompatActivity() , OnClickListener{

    private lateinit var binding: ActivityLoginBinding
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recibo el nombre de usuario serializado; y lo casteo a "Usuario"
        if (intent.getSerializableExtra("Usuario") != null)
        {
            usuario = intent.getSerializableExtra("Usuario") as Usuario
            binding.editCorreo.setText(usuario!!.correo)
            binding.editPass.setText(usuario!!.password)
        }

        binding.botonLogin.setOnClickListener(this)
        binding.botonSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){

            binding.botonLogin.id->{

                if (!binding.editCorreo.text.toString().isEmpty() && !binding.editPass.text.toString().isEmpty())
                {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("correo", binding.editCorreo.text.toString())
                    startActivity(intent)
                }
            }

            binding.botonSignUp.id->{

                //Con los dos puntos; lo "transforma" a Java
                //El contexto es la pantalla
                val intent = Intent(applicationContext, SignupActivity::class.java)
                startActivity(intent)

                //Elimino la pantalla de la memoria
                finish()
            }
        }
    }
}