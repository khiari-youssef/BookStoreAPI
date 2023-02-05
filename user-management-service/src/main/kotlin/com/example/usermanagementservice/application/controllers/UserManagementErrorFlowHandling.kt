package com.example.usermanagementservice.application.controllers

import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.entities.BookUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity



fun Flow<Boolean>.handleAuthentificationServiceResponseResult(): Flow<ResponseEntity<Boolean>> =
    map {
        ResponseEntity.ok(it)
    }
    .catch { ex ->
            ex.printStackTrace()
            val badRequest = ResponseEntity.badRequest().build<Boolean>()
            emit(
                if (ex is DomainException) {
                    when (ex) {
                        is DomainException.UnauthorizedDomainException -> {
                            ResponseEntity.of(
                                ProblemDetail.forStatus(401)
                            ).build()
                        }

                        is DomainException.AuthentificationDomainException -> {
                            ResponseEntity.of(
                                ProblemDetail.forStatus(401)
                            ).build()
                        }

                        is DomainException.InternalErrorDomainException -> {
                            ResponseEntity.internalServerError().build()
                        }

                        is DomainException.ResourceNotFoundDomainException -> {
                            badRequest
                        }

                        is DomainException.RegistrationException -> {
                            ResponseEntity.notFound().build()
                        }
                    }
                } else badRequest
            )
        }


fun Flow<BookUser?>.handleRegistrationServiceResponseResult(): Flow<ResponseEntity<BookUser>> =
    map {
        ResponseEntity.ok(it)
    }
    .catch { ex ->
            ex.printStackTrace()
            val badRequest = ResponseEntity.badRequest().build<BookUser>()
            emit(
                if (ex is DomainException) {
                    when (ex) {
                        is DomainException.UnauthorizedDomainException -> {
                            ResponseEntity.of(
                                ProblemDetail.forStatus(401)
                            ).build()
                        }

                        is DomainException.AuthentificationDomainException -> {
                            ResponseEntity.of(
                                ProblemDetail.forStatus(401)
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
                    }
                } else badRequest
            )
        }
