package com.example.usermanagementservice.application.controllers


import com.example.usermanagementservice.application.payloads.AuthPayload
import com.example.usermanagementservice.coreDomain.entities.BookUser
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationService
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@ControllerAdvice
class UserAuthExceptionHandler() : ResponseEntityExceptionHandler() {

    @ExceptionHandler(RuntimeException::class)
    fun handleException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        ex.printStackTrace()
        return ResponseEntity.of(Optional.of(mapOf("result" to "internal error")))
    }

}


@RestController
class UserAuthController @Autowired constructor(
    @Qualifier("RedisAuthService") private val authentificationService: AuthentificationService,
    @Qualifier("RedisRegistrationService") private val registrationService: RegistrationService
) {


    @PostMapping("user/login/credentials")
    suspend fun handleUserAuthentification(
        @RequestBody authCredentialsPayload: AuthPayload?
    ): Flow<ResponseEntity<Boolean>> = authentificationService
        .authenticateUser(authCredentialsPayload)
        .handleAuthentificationServiceResponseResult()


    @PostMapping("user/signup")
    suspend fun handleUserRegistration(
        @RequestBody bookUser: BookUser?
    ): Flow<ResponseEntity<BookUser>> = registrationService.registerUser(bookUser)
        .handleRegistrationServiceResponseResult()







}