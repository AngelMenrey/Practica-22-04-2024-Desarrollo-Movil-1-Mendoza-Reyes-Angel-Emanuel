package com.example.actividad_22_04_2024

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ingreso : AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var contrasena: EditText
    private lateinit var guardar: Switch
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        correo = findViewById(R.id.edtCorreo)
        contrasena = findViewById(R.id.edtContrasena)
        guardar = findViewById(R.id.swtGuardar)

        beca = Beca()
    }

    fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnIngresar -> ingresar()
            R.id.btnBorrar -> limpiar()
        }
    }

    private fun ingresar() {
        if (correo.text.isNotBlank() && contrasena.text.isNotBlank()) {
            val usr = Usuario(correo.text.toString(), contrasena.text.toString(), true)
            if (guardar.isChecked) {
                guardarPreferencias(usr)
            }
            val intent = Intent(applicationContext, Menu::class.java)
            intent.putExtra("folio", beca.folio)
            intent.putExtra("institucion", beca.institucion)
            intent.putExtra("nombre", beca.nombre)
            intent.putExtra("apellido", beca.apellido)
            intent.putExtra("nivel", beca.nivel)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Capturar información", Toast.LENGTH_LONG).show()
        }
    }

    private fun limpiar() {
        correo.text.clear()
        contrasena.text.clear()
        correo.requestFocus()
    }

    private fun guardarPreferencias(user: Usuario) {
        val preferences: SharedPreferences =
            getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("email", user.correo)
        editor.putString("password", user.contrasena)
        editor.putBoolean("guardado", user.guardado)
        editor.apply()
    }
}
