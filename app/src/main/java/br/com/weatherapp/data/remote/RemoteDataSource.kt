package br.com.weatherapp.data.remote

import br.com.weatherapp.data.remote.response.WeatherDataResponse

// Define uma interface RemoteDataSource que declara uma função suspensa (coroutine)
// para obter dados de clima com base na latitude e longitude.
interface RemoteDataSource {

    // Função suspensa que, dada uma latitude e longitude, retorna um objeto WeatherDataResponse.
    suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse
}