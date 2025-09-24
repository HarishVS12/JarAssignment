package com.harish.jarassignment.core.util


sealed class ResultAPI<out T> {

    data class Success<out T>(val data: T) : ResultAPI<T>()
    data class Error(val errorMessage: String) : ResultAPI<Nothing>()
    object Loading : ResultAPI<Nothing>()

}