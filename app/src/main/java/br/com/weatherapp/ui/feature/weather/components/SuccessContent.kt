package br.com.weatherapp.ui.feature.weather.components

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.weatherapp.data.model.WeatherInfo


@SuppressLint("DiscouragedApi")
@Composable
fun SuccessContent(context: Context = LocalContext.current, weatherInfo: WeatherInfo) {
    val iconDrawableResID: Int = context.resources.getIdentifier(
        "weather_${weatherInfo.conditionIcon}",
        "drawable",
        context.packageName
    )

    Box {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 20.dp, end = 20.dp)
                .size(32.dp),
            content = {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp),
                )
            },
            onClick = {
                Toast
                    .makeText(context, "Ainda tenho que implementar isso", Toast.LENGTH_LONG)
                    .show()
            }
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = weatherInfo.locationName,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weatherInfo.dayOfWeek,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = iconDrawableResID),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weatherInfo.condition,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "${weatherInfo.temperature}°",
                color = Color.White,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}
