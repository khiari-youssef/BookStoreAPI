package com.example.usermanagementservice.infrastructure.security.keyManagement

import com.example.usermanagementservice.infrastructure.security.keyManagement.KeyGenerationService.Companion.Asymmetric_Encryption_Algorithm
import com.example.usermanagementservice.infrastructure.security.keyManagement.KeyGenerationService.Companion.Symmetric_Encryption_Algorithm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class JavaNativeKeyGenerators : KeyGenerationService {

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default


    override suspend fun generateSecretKey(): SecretKey = withContext(Dispatchers.Default) {
        KeyGenerator.getInstance(Symmetric_Encryption_Algorithm).generateKey()
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    override suspend fun generateSecretKey(password: String, salt: String): SecretKey = withContext(coroutineDispatcher) {
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt.toByteArray(), 1000, 256)
        SecretKeySpec(factory.generateSecret(spec).encoded, Symmetric_Encryption_Algorithm)
    }

    override suspend fun generateKeyPair(): KeyPair = withContext(coroutineDispatcher) {
        KeyPairGenerator
            .getInstance(Asymmetric_Encryption_Algorithm)
            .genKeyPair()
    }
}