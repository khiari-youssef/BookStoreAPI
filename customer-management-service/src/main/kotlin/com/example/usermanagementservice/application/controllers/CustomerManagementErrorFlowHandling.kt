package com.example.usermanagementservice.application.controllers

import com.example.usermanagementservice.coreDomain.DomainException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity



fun <T> Flow<T>.buildSuccessResponse() : Flow<ResponseEntity<T>> = map {
    ResponseEntity.ok(it)
}


fun <T> handleRequestFailures(throwable: Throwable) : ResponseEntity<T>{
    val badRequest = ResponseEntity.badRequest().build<T>()
    return if (throwable is DomainException) {
        when (throwable) {
            is DomainException.UnauthorizedDomainException -> {
                ResponseEntity.of(
                    ProblemDetail
                        .forStatus(401)
                        .apply {
                            title = "Unauthorized"
                            detail = "Unauthorized access !"
                        }
                ).build()
            }

            is DomainException.AuthentificationDomainException -> {
                ResponseEntity.of(
                    ProblemDetail.forStatus(401)
                        .apply {
                            title = "Authentification failed"
                            detail = "invalid credentials or token "
                        }
                ).build()
            }

            is DomainException.InternalErrorDomainException -> {
                ResponseEntity.internalServerError().build()
            }

            is DomainException.ResourceNotFoundDomainException -> {
                badRequest
            }

            is DomainException.RegistrationException -> {
                ResponseEntity.badRequest().build()
            }
            is DomainException.DomainDateTimeException ->{
                ResponseEntity.of(
                    ProblemDetail
                        .forStatus(400)
                        .apply {
                            title = "Malformatted request ! "
                            detail = throwable.message
                        }
                ).build()
            }
        }
    } else badRequest
}

fun <T> Flow<ResponseEntity<T>>.handleRequestFlowFailures() : Flow<ResponseEntity<T>> = catch { ex->
    emit(
        handleRequestFailures(ex)
    )
}

