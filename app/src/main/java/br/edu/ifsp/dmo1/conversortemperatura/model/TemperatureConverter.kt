package br.edu.ifsp.dmo1.conversortemperatura.model

interface TemperatureConverter {

    fun converter(temperature: Double): Double

    fun getScale(): String
}