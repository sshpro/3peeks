package com.sshpro.threepeeks.presentation

sealed class UiState<out R> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String?) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}