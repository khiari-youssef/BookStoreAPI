package com.example.customermanagementservice.application.controllers

import com.example.customermanagementservice.coreDomain.DomainException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.springframework.http.ResponseEntity


fun <T> Flow<T>.buildSuccessResponse() : Flow<ResponseEntity<T>> = map {
    ResponseEntity.ok(it)
}


fun <T> handleRequestFailures(throwable: Throwable) : ResponseEntity<T>{
    val badRequest = ResponseEntity.badRequest().build<T>()
    return if (throwable is DomainException) {
        when (throwable) {
            is DomainException.UnauthorizedDomainException -> {
                ResponseEntity
                    .status(401)
                    .build()
            }

            is DomainException.AuthentificationDomainException -> {
                ResponseEntity.status(401).build()
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
                ResponseEntity
                    .badRequest()
                    .build()
            }
        }
    } else badRequest
}

fun <T> Flow<ResponseEntity<T>>.handleRequestFlowFailures() : Flow<ResponseEntity<T>> = catch { ex->
    emit(
        handleRequestFailures(ex)
    )
}

