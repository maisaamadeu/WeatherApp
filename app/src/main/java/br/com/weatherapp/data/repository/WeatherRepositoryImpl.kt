package br.com.weatherapp.data.repository

import br.com.weatherapp.data.model.WeatherInfo
import br.com.weatherapp.data.remote.RemoteDataSource
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

// Classe WeatherRepositoryImpl que implementa a interface WeatherRepository e
// utiliza a injeção de dependência para fornecer um RemoteDataSource.
class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    // Implementação da função suspensa que obtém dados de clima e mapeia
    // para um objeto WeatherInfo simplificado.
    override suspend fun getWeatherData(lat: Float, lng: Float): WeatherInfo {
        val response = remoteDataSource.getWeatherDataResponse(lat, lng) // Chama o método remoto.
        val weather = response.weather[0] // Obtém a primeira condição climática da lista.

        // Retorna um objeto WeatherInfo preenchido com dados obtidos da resposta.
        return WeatherInfo(
            locationName = response.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = response.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd' // Verifica se o último caractere do ícone é 'd' (dia).
        )
    }
}