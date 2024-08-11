package br.com.weatherapp.ui.state

// Interface que define os diferentes estados possíveis da operação
interface AppState {
    object Initial : AppState // Estado inicial, antes de qualquer ação
    object Loading : AppState // Estado de carregamento, quando a operação está em andamento
    object Success : AppState // Estado de sucesso, quando a operação é concluída com êxito
    object Error : AppState // Estado de erro, quando ocorre uma falha na operação
}