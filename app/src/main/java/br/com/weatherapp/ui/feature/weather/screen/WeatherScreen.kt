package br.com.weatherapp.ui.feature.weather.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.weatherapp.data.model.WeatherInfo
import br.com.weatherapp.ui.feature.weather.components.ErrorContent
import br.com.weatherapp.ui.feature.weather.components.LoadingContent
import br.com.weatherapp.ui.feature.weather.components.SuccessContent
import br.com.weatherapp.ui.feature.weather.state.WeatherUiState
import br.com.weatherapp.ui.feature.weather.viewmodel.WeatherViewModel
import br.com.weatherapp.ui.state.AppState
import br.com.weatherapp.ui.theme.BlueDark
import br.com.weatherapp.ui.theme.BlueSky
import br.com.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreen(state)
}

@Composable
fun WeatherScreen(state: WeatherUiState) {
    val backgroundColor = if (state.weatherInfo?.isDay == false) Color.DarkGray else BlueSky

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = backgroundColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        when (state.state) {
            AppState.Initial, AppState.Loading -> LoadingContent()
            AppState.Error -> ErrorContent(state.errorMessage!!)
            AppState.Success -> SuccessContent(weatherInfo = state.weatherInfo!!)
        }
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreen(
            WeatherUiState(
                AppState.Success,
                WeatherInfo(
                    locationName = "Belo Horizonte",
                    conditionIcon = "01d",
                    condition = "Cloudy",
                    temperature = 32,
                    dayOfWeek = "Saturday",
                    isDay = true,
                )
            )
        )
    }
}