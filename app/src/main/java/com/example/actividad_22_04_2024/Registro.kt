package com.example.actividad_22_04_2024
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

    class Registro : AppCompatActivity() {
        private lateinit var folio: EditText
        private lateinit var institucion: EditText
        private lateinit var nombre: EditText
        private lateinit var apellido: EditText
        private lateinit var nivel: Spinner
        private var beca: Beca = Beca()
        private lateinit var nivelSel: String

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_registro)
            folio = findViewById(R.id.edtFolio)
            institucion = findViewById(R.id.edtInstitucion)
            nombre = findViewById(R.id.edtNombre)
            apellido = findViewById(R.id.edtApellido)
            nivel = findViewById(R.id.spnNivel)

            val opciones = resources.getStringArray(R.array.niveles)
            val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
            nivel.adapter = adaptador
            nivelSel = opciones[0]
            nivel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    nivelSel = opciones[p2]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                 }

            }
        }
        fun onClick(view: View?){
            when(view?.id){
                R.id.btnRegistrar -> registrar()
                R.id.btnLimpiar -> limpiar()
            }
        }

        private fun registrar(){
        if(folio.text.isNotEmpty() && folio.text.isNotBlank() && institucion.text.isNotBlank() && institucion.text.isNotEmpty()
            && nombre.text.isNotBlank() && nombre.text.isNotEmpty() && apellido.text.isNotBlank() && apellido.text.isNotEmpty()){
            beca.folio = folio.text.toString().toInt()
            beca.institucion = institucion.text.toString()
            beca.nombre = nombre.text.toString()
            beca.apellido = apellido.text.toString()
            beca.nivel = nivelSel
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("folio", beca.folio)
            intent.putExtra("institucion", beca.institucion)
            intent.putExtra("nombre", beca.nombre)
            intent.putExtra("apellido", beca.apellido)
            intent.putExtra("nivel", beca.nivel)
        }else{
            Toast.makeText(this, "Capturar informacion", Toast.LENGTH_SHORT).show()
        }
        }

        private fun limpiar(){
            folio.text = null
            institucion.text = null
            nombre.text = null
            apellido.text = null
            nivel.requestFocus()
        }

    }
