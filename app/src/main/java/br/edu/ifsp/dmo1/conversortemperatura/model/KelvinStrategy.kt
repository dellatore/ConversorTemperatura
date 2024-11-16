package br.edu.ifsp.dmo1.conversortemperatura.model

object KelvinStrategy:  TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return temperature + 273
    }

    override fun getScale(): String {
        return "K"
    }
}