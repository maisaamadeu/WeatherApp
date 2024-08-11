package br.com.weatherapp.data.model

// Data class WeatherInfo que representa informações climáticas simplificadas para uso no aplicativo.
data class WeatherInfo(
    val locationName: String, // Nome da localização.
    val conditionIcon: String, // Ícone representando a condição climática.
    val condition: String, // Condição climática principal (ex: "Clear").
    val temperature: Int, // Temperatura arredondada.
    val dayOfWeek: String, // Dia da semana atual.
    val isDay: Boolean // Booleano que indica se é dia ou noite.
)
