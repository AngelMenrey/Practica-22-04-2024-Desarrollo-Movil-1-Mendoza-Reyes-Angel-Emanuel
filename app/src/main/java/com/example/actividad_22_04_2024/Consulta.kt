package com.example.actividad_22_04_2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Consulta : AppCompatActivity() {
    private lateinit var info : TextView
    private var beca : Beca = Beca()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)
        info = findViewById(R.id.txtInfo)
        val infoRecibida = intent.extras
        beca.folio = infoRecibida?.getInt("folio")!!
        beca.institucion = infoRecibida?.getString("institucion")!!
        beca.nombre = infoRecibida?.getString("nombre")!!
        beca.apellido = infoRecibida?.getString("apellido")!!
        beca.nivel = infoRecibida?.getString("nivel")!!

        info.text = "Informacion registrada\n" +
                    "Folio: ${beca.folio}\n" +
                    "Institucion: ${beca.institucion}\n" +
                    "Nombre: ${beca.nombre}\n" +
                    "Apellido: ${beca.apellido}\n" +
                    "Nivel: ${beca.nivel}"
    }
}