package com.example.comunicacion.data

import com.example.comunicacion.R
import com.example.comunicacion.model.Marca

class Dataset {

    //Aqui quiero tener la lista de todos los datos que maneja la app
    //quiero que estos datos sean capturados de forma automatica
    // no necesito tener la instancia del objeto para ejecutar un metodo

    companion object{

        fun getAllMarcas(): ArrayList<Marca>{

            val listaMarcas = ArrayList<Marca>()

            listaMarcas.add(Marca("Audi", R.drawable.audi, "premium"))
            listaMarcas.add(Marca("Mercedes", R.drawable.mercedes, "VIP"))
            listaMarcas.add(Marca("Ford", R.drawable.ford, "bueno"))
            listaMarcas.add(Marca("VW", R.drawable.vw, "alto nivel"))
            return listaMarcas;
        }
    }
}