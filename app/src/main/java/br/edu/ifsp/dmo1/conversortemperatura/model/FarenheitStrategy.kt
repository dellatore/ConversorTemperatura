package br.edu.ifsp.dmo1.conversortemperatura.model

object FahrenheitStrategy: TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return 1.8 * temperature + 32
    }
    override fun getScale(): String {
        return "ºF"
    }
}
