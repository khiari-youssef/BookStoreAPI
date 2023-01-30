package com.example.usermanagementservice.infrastructure.security.encryption

import com.example.usermanagementservice.infrastructure.security.keyManagement.JavaNativeKeyGenerators
import com.example.usermanagementservice.infrastructure.security.keyManagement.KeyGenerationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class JavaNativeEncryptionServicesEncryptionTestCases {


    private val encryptionService: JavaNativeEncryptionServices = JavaNativeEncryptionServices()

    private val keyGenerationService : KeyGenerationService = JavaNativeKeyGenerators()


    private val data : String = "Hello, this is a spring boot web application"

    private val password : String = "00000000"

    private val salt : String = "salt0000"


    @DisplayName("when Testing symmetric encryption with valid randomly generated secret key, then test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun secretKeyGenerationTestingSuccessCase() = runTest {
        Assertions.assertDoesNotThrow {
            launch {
                keyGenerationService.generateSecretKey()
            }
        }
        advanceUntilIdle()
    }

    @DisplayName("when Testing symmetric encryption with valid password based generated secret key, then test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun passwordBasedSecretKeyGenerationTestingSuccessCase() = runTest {
        Assertions.assertDoesNotThrow {
            launch {
                keyGenerationService.generateSecretKey(
                    password,
                    salt
                )
            }
        }
        advanceUntilIdle()
    }

    @DisplayName("when Testing symmetric encryption with valid plain text input, Then encryption test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun symmetricPlainTextEncryptionTestingSuccessCase() = runTest {
       val secret = keyGenerationService.generateSecretKey()
        Assertions.assertDoesNotThrow{
           launch {
              encryptionService.encrypt(data.toByteArray(),secret)
           }
        }
        advanceUntilIdle()
    }

    @DisplayName("when Testing symmetric encryption with valid base64 encoded plain text input, Then encryption test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun symmetricBase64EncodedPlainTextEncryptionTestingSuccessCase() = runTest {
       val secret = keyGenerationService.generateSecretKey()
        Assertions.assertDoesNotThrow{
           launch {
              encryptionService.encrypt(data.toByteArray(),secret,true)
           }
        }
        advanceUntilIdle()
    }


    @DisplayName("when Testing symmetric decryption with valid encrypted plain text input and same algorithm specs, Then decryption test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun symmetricDecryptionToPlainTextTestingSuccessCase() = runTest {
       val secret = keyGenerationService.generateSecretKey()
        Assertions.assertDoesNotThrow{
           launch {
              val encryptedData = encryptionService.encrypt(data.toByteArray(Charsets.UTF_8),secret)
              val decryptedData  = encryptionService.decrypt(encryptedData,secret)
               Assertions.assertEquals(
                   data,
                   decryptedData.toString(Charsets.UTF_8)
               )
           }
        }
        advanceUntilIdle()
    }



    @DisplayName("when Testing symmetric decryption with valid encrypted base64 input and same algorithm specs, Then decryption test should succeed")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun symmetricDecryptionToBase64TestingSuccessCase() = runTest {
       val secret = keyGenerationService.generateSecretKey()
        Assertions.assertDoesNotThrow{
           launch {
              val encryptedData = encryptionService.encrypt(data.toByteArray(Charsets.UTF_8),secret,true)
              val decryptedData  = encryptionService.decrypt(encryptedData,secret,true)
               Assertions.assertEquals(
                   data,
                   decryptedData.toString(Charsets.UTF_8)
               )
           }
        }
        advanceUntilIdle()
    }

}