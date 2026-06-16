package com.example.domain.extension

import com.example.domain.model.OperationResult

inline fun OperationResult.Failure.onFailure(
    onInvalidToken: () -> Unit = {},
    onNetworkError: () -> Unit = {},
    onUnknownError: (String) -> Unit = {},
) {
    when (this) {
        is OperationResult.Failure.InvalidToken -> onInvalidToken()

        is OperationResult.Failure.NetworkError -> onNetworkError()

        is OperationResult.Failure.Unknown -> onUnknownError(this.message)
    }
}