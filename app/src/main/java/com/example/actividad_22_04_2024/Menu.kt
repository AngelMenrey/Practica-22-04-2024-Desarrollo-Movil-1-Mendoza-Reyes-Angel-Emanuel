package com.example.actividad_22_04_2024

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class Menu : AppCompatActivity() {
    private  lateinit var beca: Beca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var toolbar : Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)
        beca = Beca()
        var infoRecibida = intent.extras

        if(infoRecibida != null){
            beca.folio = infoRecibida?.getInt("folio")!!
            beca.institucion = infoRecibida?.getString("institucion")!!
            beca.nombre = infoRecibida.getString("nombre")!!
            beca.apellido = infoRecibida.getString("apellido")!!
            beca.nivel = infoRecibida.getString("nivel")!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_desplegable, menu)
        return true
    }

    override fun onOptionsItemSelected(item:MenuItem) : Boolean{
        val intent : Intent?
        when(item.itemId){
            R.id.itmFormulario ->{
                intent = Intent(applicationContext, Registro::class.java)
                startActivity(intent)
            }
            R.id.itmConsultar->{
                intent = Intent(applicationContext, Consulta::class.java)
                intent.putExtra("folio", beca.folio)
                intent.putExtra("institucion", beca.institucion)
                intent.putExtra("nombre", beca.nombre)
                intent.putExtra("apellido", beca.apellido)
                intent.putExtra("nivel", beca.nivel)
                startActivity(intent)
            }
            R.id.itmCerrar -> { cerrarSesion()}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion(){
    val preferences : SharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)
        val editor : SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(applicationContext, Ingreso::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}