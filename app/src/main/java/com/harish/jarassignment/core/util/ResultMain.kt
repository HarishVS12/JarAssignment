package com.harish.jarassignment.core.util


sealed class ResultMain<out T> {

    data class Success<out T>(val data: T) : ResultMain<T>()
    data class Error(val errorMessage: String) : ResultMain<Nothing>()
    object Loading : ResultMain<Nothing>()

}