package com.example.topicosproyect

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {

    private var materia : String = ""
    private var horasPracticas : String = ""
    private var horasTeoricas : String = ""
    private var tiempoIndependiente : String = ""

    private lateinit var etMateria : TextView
    private lateinit var etHorasTeoricas : TextView
    private lateinit var etHorasPracticas : TextView
    private lateinit var etTrabajoIndependiente : TextView

    private lateinit var btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        initComponent()
        recibirDatos()
        setDatos()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponent() {
        etMateria = findViewById(R.id.tvMateriaName)
        etHorasTeoricas = findViewById(R.id.tvHt)
        etHorasPracticas =  findViewById(R.id.tvHp)
        etTrabajoIndependiente =  findViewById(R.id.tvTi)
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun recibirDatos() {
        materia = intent.getStringExtra("materia").toString()
        horasPracticas = intent.getStringExtra("hp").toString()
        horasTeoricas = intent.getStringExtra("ht").toString()
        tiempoIndependiente = intent.getStringExtra("ti").toString()
    }

    private fun setDatos() {
        etMateria.setText(materia)
        etHorasTeoricas.setText(horasTeoricas)
        etHorasPracticas.setText(horasPracticas)
        etTrabajoIndependiente.setText(tiempoIndependiente)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
