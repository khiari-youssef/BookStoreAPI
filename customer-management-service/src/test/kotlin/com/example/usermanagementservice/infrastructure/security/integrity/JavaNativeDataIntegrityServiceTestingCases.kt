package com.example.usermanagementservice.infrastructure.security.integrity


import com.example.customermanagementservice.infrastructure.security.integrity.JavaNativeDataIntegrityService
import com.example.customermanagementservice.infrastructure.security.keyManagement.JavaNativeKeyGenerators
import com.example.customermanagementservice.infrastructure.security.keyManagement.KeyGenerationService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JavaNativeDataIntegrityServiceTestingCases {

    private val dataIntegrityServices  = JavaNativeDataIntegrityService()

    private val keyGenerationService : KeyGenerationService = JavaNativeKeyGenerators()

    private val password: String = "00000000"

    private val salt: String = "salt0000"



    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when we apply pbkdf2 to password,key should be successfully generated!")
    @Test
    fun pbkdf2Testing() = runTest {
        Assertions.assertEquals(
            dataIntegrityServices.pbkdf2(
                data = password.toCharArray(),
                salt = salt.toByteArray()
            ).size,
            256/8
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when digital signature is applied, not error should take place")
    @Test
    fun testingDataDigitalSignature() = runTest {

        dataIntegrityServices.signData(
            password.toByteArray(),
            keyGenerationService.generateKeyPair().private
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("when digital signature is applied, it should be verified correctly without alteration")
    @Test
    fun testingDataDigitalSignatureVerification() = runTest {

        val keypair = keyGenerationService.generateKeyPair()

       val signature = dataIntegrityServices.signData(
            password.toByteArray(),
           keypair.private
        )

        dataIntegrityServices.verifyData(
            data = password.toByteArray(),
            signature = signature,
            publicKey = keypair.public
        )

    }



}