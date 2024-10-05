package com.example.topicosproyect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.topicosproyect.Result
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var ht : Int = 0
    private var hp : Int = 0
    private var horasPresenciales : Int = 0
    private var horasPorCreditos: Int = 0
    private var trabajoIndependiente: Int = 0

    private lateinit var tvHorasTeorica : TextView
    private lateinit var buttonHTRemove : FloatingActionButton
    private lateinit var buttonHTAdd : FloatingActionButton

    private lateinit var tvHorasPractica : TextView
    private lateinit var buttonHPRemove:  FloatingActionButton
    private lateinit var buttonHPAdd : FloatingActionButton

    private lateinit var etMateria : EditText
    private lateinit var etCreditos : EditText
    private lateinit var btnCalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponent()
        initListener()
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponent(){
        tvHorasTeorica = findViewById(R.id.tvHorasTeorica)
        buttonHTRemove = findViewById(R.id.buttonHTRemove)
        buttonHTAdd =  findViewById(R.id.buttonHTAdd)

        tvHorasPractica = findViewById(R.id.tvHorasPractica)
        buttonHPRemove = findViewById(R.id.buttonHPRemove)
        buttonHPAdd = findViewById(R.id.buttonHPAdd)

        etMateria = findViewById(R.id.etMateria)
        etCreditos = findViewById(R.id.etCreditos)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun initListener(){
        buttonHTAdd.setOnClickListener {
            ht += 1
            setHT()
        }
        buttonHTRemove.setOnClickListener {
            if(ht >= 1){
                ht -= 1
                setHT()
            }
        }
        buttonHPAdd.setOnClickListener{
            hp += 1
            setHP()
        }
        buttonHPRemove.setOnClickListener{
            if( hp >= 1){
                hp -= 1
                setHP()
            }
        }

        btnCalcular.setOnClickListener {
            if (etMateria.text.toString() != "") {
                if (hp > 0 && ht > 0){
                   horasPresenciales = (ht - hp) * 16
                    val intCredito = etCreditos.text.toString()
                    if (intCredito.isNotEmpty()) {
                        if (intCredito.toInt() > 0) {
                            horasPorCreditos = intCredito.toInt() * 48
                            trabajoIndependiente = (horasPorCreditos - horasPresenciales)/16

                                val intent = Intent(this, Result::class.java)
                                intent.putExtra("materia", etMateria.text.toString())
                                intent.putExtra("hp", hp)
                                intent.putExtra("ht", ht)
                                intent.putExtra("ti", trabajoIndependiente)
                                startActivity(intent)
                        } else {
                            Toast.makeText(this, "Número de creditos debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Debe ingresar Horas Practicas o Horas teoricas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese el nombre de la materia", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setHT () {
        tvHorasTeorica.text = ht.toString()
    }

    private fun setHP(){
        tvHorasPractica.text = hp.toString()
    }


    private fun initUI(){
        setHT()
        setHP()
    }
}