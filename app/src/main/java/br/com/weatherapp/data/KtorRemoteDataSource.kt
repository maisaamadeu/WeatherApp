package br.com.weatherapp.data

import br.com.weatherapp.data.remote.RemoteDataSource
import br.com.weatherapp.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

// Classe KtorRemoteDataSource que implementa a interface RemoteDataSource.
// Utiliza a injeção de dependência (via @Inject) para fornecer um HttpClient.
class KtorRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {

    // Companion object para definir constantes. BASE_URL é a URL base da API de clima.
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    // Implementação da função suspensa para buscar dados de clima.
    // A URL é construída com a latitude, longitude, e uma chave de API.
    override suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse {
        return httpClient
            .get("${BASE_URL}/weather?lat=$lat&lon=$lng&appid=7f30f5465f7ee21fa23a0cff1905322a&units=metric")
            .body() // Converte a resposta para um objeto WeatherDataResponse.
    }
}
