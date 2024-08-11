package br.com.weatherapp.data.di

import br.com.weatherapp.data.KtorRemoteDataSource
import br.com.weatherapp.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Anotação @Module indica que essa interface é um módulo de injeção de dependências para Hilt.
@Module
@InstallIn(SingletonComponent::class) // O ciclo de vida do módulo é vinculado ao SingletonComponent (escopo da aplicação).
interface DataSourceModule {

    // @Binds indica que estamos vinculando a implementação KtorRemoteDataSource
    // à interface RemoteDataSource para que Hilt saiba qual implementação usar.
    @Binds
    @Singleton // Singleton garante que a implementação será usada como uma instância única.
    fun bindRemoteDataSource(remoteDataSource: KtorRemoteDataSource): RemoteDataSource
}
