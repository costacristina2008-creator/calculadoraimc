package com.example.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPeso = findViewById<EditText>(R.id.etPeso)
        val etAltura = findViewById<EditText>(R.id.etAltura)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val pesoTexto = etPeso.text.toString().replace(",", ".")
            val alturaTexto = etAltura.text.toString().replace(",", ".")

            val peso = pesoTexto.toDoubleOrNull()
            val altura = alturaTexto.toDoubleOrNull()

            if (peso == null || altura == null || altura <= 0 || peso <= 0) {
                tvResultado.text = getString(R.string.erro_entrada)
            } else {
                val imc = peso / (altura * altura)
                val classificacao = obterClassificacaoIMC(imc)

                tvResultado.text = String.format(Locale.getDefault(), "IMC: %.2f\n%s", imc, classificacao)
            }
        }
    }

    private fun obterClassificacaoIMC(imc: Double): String {
        return when {
            imc < 18.5 -> getString(R.string.abaixo_peso)
            imc in 18.5..24.9 -> getString(R.string.peso_normal)
            imc in 25.0..29.9 -> getString(R.string.sobrepeso)
            imc in 30.0..34.9 -> getString(R.string.obesidade_1)
            imc in 35.0..39.9 -> getString(R.string.obesidade_2)
            else -> getString(R.string.obesidade_3)
        }
    }
}