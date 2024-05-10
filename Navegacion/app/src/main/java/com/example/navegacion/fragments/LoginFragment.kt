package com.example.navegacion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navegacion.databinding.FragmentLoginBinding

//Se que es una extension, porque le pongo los parentesis
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    //Asocia la parte logica con la parte grafica
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}