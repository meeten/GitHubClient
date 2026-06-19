package com.example.data.extension

import com.example.domain.model.OperationResult
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import java.net.UnknownHostException

suspend fun <R> safeNetworkCall(
    call: suspend () -> HttpResponse,
    transform: suspend (HttpResponse) -> R
): OperationResult<R> {
    return try {
        val response = call()

        when (response.status) {
            HttpStatusCode.OK -> {
                OperationResult.Success(transform(response))
            }

            in HttpStatusCode.BadRequest..HttpStatusCode.NotFound -> {
                OperationResult.Failure.InvalidToken
            }

            else -> {
                OperationResult.Failure.Unknown(message = response.status.description)
            }
        }
    } catch (e: UnknownHostException) {
        OperationResult.Failure.NetworkError
    } catch (e: Exception) {
        OperationResult.Failure.Unknown(message = e.message ?: "Unknown error")
    }
}