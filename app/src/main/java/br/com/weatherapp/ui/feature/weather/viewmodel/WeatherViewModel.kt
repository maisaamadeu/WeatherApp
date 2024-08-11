package br.com.weatherapp.ui.feature.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.weatherapp.data.model.WeatherInfo
import br.com.weatherapp.data.repository.WeatherRepository
import br.com.weatherapp.ui.feature.weather.state.WeatherUiState
import br.com.weatherapp.ui.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Anotação @HiltViewModel indica que a classe WeatherViewModel é um ViewModel que
// utilizará injeção de dependência com Hilt.
@HiltViewModel
class WeatherViewModel @Inject constructor(
    // O repositório de dados de clima é injetado para fornecer os dados necessários ao ViewModel.
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    // Estado interno do ViewModel usando StateFlow, inicializado com o estado inicial
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    // Bloco de inicialização que é executado quando o ViewModel é criado.
    // Chama a função gerWeatherInfo para iniciar o processo de busca das informações climáticas.
    init {
        gerWeatherInfo()
    }

    // Função privada que inicia a busca das informações climáticas.
    private fun gerWeatherInfo() {
        // Executa a operação de busca dentro do escopo de ViewModel para gerenciar o ciclo de vida.
        viewModelScope.launch {
            // Atualiza o estado para Loading para indicar que a operação de busca está em andamento.
            updateState(AppState.Loading)

            try {
                // Obtém as informações climáticas do repositório usando coordenadas específicas.
                val weatherInfo = weatherRepository.getWeatherData(-23.2854f, -47.674f)

                // Atualiza o estado para Success com as informações climáticas obtidas.
                updateState(
                    AppState.Success,
                    weatherInfo
                )

            } catch (e: Exception) {
                // Em caso de erro, atualiza o estado para Error com uma mensagem de erro.
                updateState(
                    AppState.Error,
                    errorMessage = e.message
                        ?: "Ocorreu um erro misterioso ao buscar o clima desse local!"
                )
            }
        }
    }

    // Função privada que atualiza o estado de AppState com o novo estado fornecido.
    private fun updateState(
        newState: AppState,
        weatherInfo: WeatherInfo? = null,
        errorMessage: String? = null
    ) {
        _uiState.value = _uiState.value.copy(
            state = newState,
            weatherInfo = weatherInfo,
            errorMessage = errorMessage
        )
    }
}