package com.example.network.data

sealed class DataState<T> {

    data class Success<T> (val data: T) : DataState<T>()

    data class Error<T>(val uiComponent: UIComponent) : DataState<T>()

}

sealed class UIComponent(){
    data class Toast(val text: String) : UIComponent()
}
