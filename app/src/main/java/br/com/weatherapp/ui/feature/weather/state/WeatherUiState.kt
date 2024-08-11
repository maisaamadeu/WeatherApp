package br.com.weatherapp.ui.feature.weather.state

import br.com.weatherapp.data.model.WeatherInfo
import br.com.weatherapp.ui.state.AppState

// Data class imutável que encapsula o estado completo da UI
data class WeatherUiState(
    val state: AppState = AppState.Initial, // Estado atual, definido pela interface AppState
    val weatherInfo: WeatherInfo? = null, // Informações climáticas, presente apenas no estado de sucesso
    val errorMessage: String? = null // Mensagem de erro, presente apenas no estado de erro
)


