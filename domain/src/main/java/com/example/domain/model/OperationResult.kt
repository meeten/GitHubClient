package com.example.domain.model

sealed interface OperationResult<out T> {

    data class Success<T>(val data: T) : OperationResult<T>

    sealed interface Failure : OperationResult<Nothing> {

        object InvalidToken : Failure

        object NetworkError : Failure

        data class Unknown(val message: String) : Failure
    }
}