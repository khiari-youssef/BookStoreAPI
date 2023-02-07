package com.example.customermanagementservice.infrastructure.security.integrity

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


class JavaNativeDataIntegrityService : DataIntegrityServices{

    private val coroutineDispatcher  : CoroutineDispatcher = Dispatchers.Default

    override suspend fun signData(data: ByteArray, privateKey: PrivateKey, algorithm: String): ByteArray = withContext(coroutineDispatcher){
        val s = Signature.getInstance(algorithm)
            .apply {
                initSign(privateKey)
                update(data)
            }
        s.sign()
    }

    override suspend fun verifyData(data: ByteArray, publicKey: PublicKey,signature : ByteArray, algorithm: String): Boolean = withContext(coroutineDispatcher) {
        val s = Signature.getInstance(algorithm)
            .apply {
                initVerify(publicKey)
                update(data)
            }
        s.verify(signature)
    }

    override suspend fun pbkdf2(data: CharArray, iterations: Int, salt: ByteArray, keyLength: Int): ByteArray = withContext(coroutineDispatcher){
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(data, salt, iterations, keyLength)
        factory.generateSecret(spec).encoded
    }


}