package br.edu.ifsp.dmo1.conversortemperatura.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.conversortemperatura.R
import br.edu.ifsp.dmo1.conversortemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.conversortemperatura.model.CelsiusStrategy
import br.edu.ifsp.dmo1.conversortemperatura.model.FahrenheitStrategy
import br.edu.ifsp.dmo1.conversortemperatura.model.KelvinStrategy
import br.edu.ifsp.dmo1.conversortemperatura.model.TemperatureConverter
import kotlin.NumberFormatException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: TemperatureConverter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnCelsius.setOnClickListener {
            handleConversion(CelsiusStrategy)
        }
        binding.btnFahrenheit.setOnClickListener {
            handleConversion(FahrenheitStrategy)
        }
        binding.btnKelvin.setOnClickListener{
            handleConversion(KelvinStrategy)
        }
    }

    private fun readTemperature(): Double {
        return try {
            binding.edittextTemperature.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            throw NumberFormatException("Input Error")
        }
    }

    private fun handleConversion(strategy: TemperatureConverter) {
        converterStrategy = strategy
        try {
            val inputValue = readTemperature()
            binding.textviewResultNumber.text = String.format(
                "%.2f %s",
                converterStrategy.converter(inputValue),
                converterStrategy.getScale()
            )
            binding.textviewResultMessage.text = if (this.converterStrategy is
                        CelsiusStrategy
            ) {
                getString(R.string.msgFtoC)
            }else if (this.converterStrategy is KelvinStrategy){
              getString(R.string.msgCtoK)
            } else {
                getString(R.string.msgCtoF)
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                getString(R.string.error_popup_notify),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }
    }
}