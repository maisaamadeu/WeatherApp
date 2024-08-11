package br.com.weatherapp.data.remote.response

// Importa as anotações e funcionalidades necessárias para serialização com kotlinx.serialization.
// @Serializable permite que as classes sejam serializadas/deserializadas para/de JSON.
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Anotação @Serializable indica que a classe WeatherDataResponse pode ser serializada
// para JSON e desserializada de JSON.
@Serializable
data class WeatherDataResponse(
    // Coord é uma subestrutura que contém as coordenadas geográficas (longitude e latitude).
    val coord: Coord,

    // Lista de objetos Weather que descrevem as condições climáticas atuais (como descrição, ícone, etc.).
    val weather: List<Weather>,

    // Base de dados usada para as informações meteorológicas.
    val base: String,

    // Main contém informações principais como temperatura, pressão e umidade.
    val main: Main,

    // Visibilidade em metros.
    val visibility: Int,

    // Wind contém informações sobre a velocidade e direção do vento.
    val wind: Wind,

    // Rain contém informações sobre a quantidade de chuva, se houver. Pode ser nulo.
    val rain: Rain? = null,

    // Clouds contém informações sobre a quantidade de cobertura de nuvens.
    val clouds: Clouds,

    // Data/hora da medição das informações em segundos desde a era Unix.
    val dt: Long,

    // Sys contém informações do sistema, como o país e horários do nascer/pôr do sol. Pode ser nulo.
    val sys: Sys? = null,

    // Deslocamento de fuso horário em segundos.
    val timezone: Int,

    // ID da cidade.
    val id: Long,

    // Nome da cidade.
    val name: String,

    // Código de resposta (200 significa sucesso, por exemplo).
    val cod: Int
)

// Anotação @Serializable indica que a classe Coord pode ser serializada/deserializada para/de JSON.
// Coord contém informações sobre as coordenadas geográficas: longitude e latitude.
@Serializable
data class Coord(
    val lon: Double, // Longitude
    val lat: Double  // Latitude
)

// Anotação @Serializable indica que a classe Weather pode ser serializada/deserializada para/de JSON.
// Weather contém informações sobre as condições climáticas.
@Serializable
data class Weather(
    val id: Int,            // ID do clima (código que representa o tipo de clima).
    val main: String,       // Condição principal do clima (ex: "Clear", "Rain").
    val description: String,// Descrição detalhada do clima (ex: "light rain").
    val icon: String        // Nome do ícone representando o clima.
)

// Anotação @Serializable indica que a classe Main pode ser serializada/deserializada para/de JSON.
// Main contém informações sobre as condições principais do clima, como temperatura e umidade.
@Serializable
data class Main(
    val temp: Double,       // Temperatura atual.

    // Anotação @SerialName renomeia o campo na serialização para JSON.
    // Por exemplo, feelsLike será serializado como "feels_like".
    @SerialName("feels_like") val feelsLike: Double, // Temperatura que parece (sensação térmica).

    @SerialName("temp_min") val tempMin: Double,     // Temperatura mínima.
    @SerialName("temp_max") val tempMax: Double,     // Temperatura máxima.
    val pressure: Int,       // Pressão atmosférica.
    val humidity: Int,       // Umidade relativa do ar.

    // @SerialName renomeia os campos opcionais para JSON, se forem fornecidos.
    @SerialName("sea_level") val seaLevel: Int? = null,  // Pressão ao nível do mar (opcional).
    @SerialName("grnd_level") val groundLevel: Int? = null // Pressão ao nível do solo (opcional).
)

// Anotação @Serializable indica que a classe Wind pode ser serializada/deserializada para/de JSON.
// Wind contém informações sobre a velocidade e direção do vento.
@Serializable
data class Wind(
    val speed: Double,      // Velocidade do vento.
    val deg: Int,           // Direção do vento em graus.
    val gust: Double? = null // Rajada de vento, se houver (opcional).
)

// Anotação @Serializable indica que a classe Rain pode ser serializada/deserializada para/de JSON.
// Rain contém informações sobre a precipitação de chuva.
@Serializable
data class Rain(
    @SerialName("1h") val oneHour: Double // Quantidade de chuva nas últimas 1 hora.
)

// Anotação @Serializable indica que a classe Clouds pode ser serializada/deserializada para/de JSON.
// Clouds contém informações sobre a quantidade de nuvens.
@Serializable
data class Clouds(
    val all: Int // Percentual de cobertura de nuvens.
)

// Anotação @Serializable indica que a classe Sys pode ser serializada/deserializada para/de JSON.
// Sys contém informações do sistema, como o país e horários do nascer/pôr do sol.
@Serializable
data class Sys(
    val type: Int? = null,  // Tipo de sistema (opcional).
    val id: Int? = null,    // ID do sistema (opcional).
    val country: String,    // Código do país (ex: "US" para Estados Unidos).
    val sunrise: Long,      // Hora do nascer do sol em segundos desde a era Unix.
    val sunset: Long        // Hora do pôr do sol em segundos desde a era Unix.
)
