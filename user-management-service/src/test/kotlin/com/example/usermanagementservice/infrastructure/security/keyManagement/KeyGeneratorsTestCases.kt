package com.example.usermanagementservice.infrastructure.security.keyManagement

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class KeyGeneratorsTestCases {

    private val keyGenerationService : KeyGenerationService = JavaNativeKeyGenerators()

    private val password: String = "00000000"

    private val salt: String = "salt0000"

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when generating a secret key with a valid algorithm, then test should succeed")
    @Test
    @Order(1)
    fun secretKeyGenerationTesting() = runTest{
        Assertions.assertDoesNotThrow {
            launch {
                keyGenerationService.generateSecretKey()
            }
        }
        advanceUntilIdle()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when generating a secret key with a valid password and pbkdf2 algorithm, then test should succeed")
    @Test
    @Order(2)
    fun passwordBasedSecretKeyGenerationTesting() = runTest{
        Assertions.assertDoesNotThrow {
            launch {
                keyGenerationService.generateSecretKey(
                    password,salt
                )
            }
        }
        advanceUntilIdle()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when generating a  key pair with a valid algorithm, then test should succeed")
    @Test
    @Order(3)
    fun keyPairGenerationTesting() = runTest{
       Assertions.assertDoesNotThrow {
          launch {
              keyGenerationService.generateKeyPair()
          }
       }
        advanceUntilIdle()
    }


}