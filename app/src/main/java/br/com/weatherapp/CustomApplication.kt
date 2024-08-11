package br.com.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// A anotação @HiltAndroidApp é usada para marcar a classe Application em um aplicativo Android
// como o ponto de entrada para a injeção de dependências com Hilt.
// Isso prepara o aplicativo para a injeção de dependências em todo o ciclo de vida da aplicação.
@HiltAndroidApp
class CustomApplication : Application()
