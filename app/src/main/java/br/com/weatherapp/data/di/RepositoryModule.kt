package br.com.weatherapp.data.di

import br.com.weatherapp.data.repository.WeatherRepository
import br.com.weatherapp.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// Anotação @Module indica que essa interface é um módulo de injeção de dependências para Hilt.
@Module
@InstallIn(ViewModelComponent::class) // O ciclo de vida do módulo é vinculado ao ViewModelComponent.
interface RepositoryModule {

    // @Binds indica que estamos vinculando a implementação WeatherRepositoryImpl
    // à interface WeatherRepository para que Hilt saiba qual implementação usar.
    @Binds
    fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}