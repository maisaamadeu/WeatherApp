package br.com.weatherapp.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

// Anotação @Module indica que esta classe é um módulo de dependências do Hilt,
// usada para fornecer instâncias de objetos a serem injetados em outras partes do aplicativo.
@Module

// Anotação @InstallIn define o ciclo de vida do módulo. No caso, SingletonComponent significa
// que as dependências fornecidas por este módulo estarão disponíveis em toda a aplicação enquanto ela existir.
@InstallIn(SingletonComponent::class)
class RemoteModule {

    // Anotação @Provides indica que esta função é um provedor de dependência.
    // Quando uma classe precisar de um HttpClient, o Hilt usará essa função para fornecer a instância.
    @Provides

    // Anotação @Singleton garante que o HttpClient criado aqui será uma instância única (singleton)
    // em toda a aplicação, ou seja, sempre que solicitado, será a mesma instância.
    @Singleton
    fun provideHttpClient(): HttpClient {

        // Retorna uma instância de HttpClient configurada com algumas funcionalidades adicionais.
        return HttpClient(Android) {

            // Instala o recurso de Logging no HttpClient, permitindo que as requisições e respostas
            // HTTP sejam logadas. Aqui, o tipo de logger usado é o SIMPLE, que fornece um log básico.
            install(Logging) {
                logger = Logger.SIMPLE
            }

            // Instala o recurso de ContentNegotiation no HttpClient. Isso permite negociar o tipo de
            // conteúdo das requisições e respostas, como JSON, XML, etc. Aqui, configuramos para
            // trabalhar com JSON e definimos algumas opções de formatação.
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true  // Formata o JSON para ser mais legível, com indentação e quebras de linha.
                        isLenient = true    // Permite que o parser JSON seja mais flexível ao interpretar a estrutura do JSON.
                    }
                )
            }
        }
    }
}
