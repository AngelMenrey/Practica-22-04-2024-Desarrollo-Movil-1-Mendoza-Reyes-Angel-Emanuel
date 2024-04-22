package com.example.actividad_22_04_2024

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beca = Beca()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (nuevoUsuario()) {
                    intent = Intent(applicationContext, Menu::class.java)
                    intent.putExtra("folio", beca.folio)
                    intent.putExtra("institucion", beca.institucion)
                    intent.putExtra("nombre", beca.nombre)
                    intent.putExtra("apellido", beca.apellido)
                    intent.putExtra("nivel", beca.nivel)
                }else{
                    Intent(applicationContext, Ingreso::class.java)
                }
                startActivity(intent)
            }
        }, 3000)
    }

    fun nuevoUsuario() : Boolean{
        val preferences : SharedPreferences=
            getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        return  preferences.getBoolean("guardado", false)
    }


}
