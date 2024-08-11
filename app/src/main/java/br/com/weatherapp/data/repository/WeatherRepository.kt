package br.com.weatherapp.data.repository

import br.com.weatherapp.data.model.WeatherInfo

// Interface WeatherRepository que declara uma função suspensa para obter dados de clima.
interface WeatherRepository {

    // Função suspensa que, dada uma latitude e longitude, retorna um objeto WeatherInfo.
    suspend fun getWeatherData(lat: Float, lng: Float): WeatherInfo
}
