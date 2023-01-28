package com.example.usermanagementservice.infrastructure.security

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


@Service
class JavaNativeEncryptionServices : EncryptionServices{

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default

    private val encryptionAlgorithm: String = "AES"

    private val ivSize : Int = 16



    override suspend fun generateSecretKey(): SecretKey = withContext(Dispatchers.Default) {
        KeyGenerator.getInstance(encryptionAlgorithm).generateKey()
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    override suspend fun generateSecretKey(password: String, salt: String): SecretKey = withContext(coroutineDispatcher) {
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt.toByteArray(), 65536, 256)
        SecretKeySpec(factory.generateSecret(spec).encoded, encryptionAlgorithm)
    }

    private fun generateIv(size: Int = ivSize): IvParameterSpec = kotlin.run {
        val iv = ByteArray(size)
        SecureRandom().nextBytes(iv)
        IvParameterSpec(iv)
    }


    override suspend fun encrypt(
        inputData: ByteArray,
        secretKey: SecretKey,
        encodeBase64: Boolean
    ): ByteArray = withContext(coroutineDispatcher) {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        cipher.doFinal( if(encodeBase64) Base64.getEncoder().encode(inputData) else inputData)
    }


    override suspend fun decrypt(
        encryptedData: ByteArray,
        secretKey: SecretKey,
        decodeBase64: Boolean
    ): ByteArray = withContext(coroutineDispatcher) {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedData = cipher.doFinal(encryptedData)
        try {
            Base64.getDecoder().decode(decryptedData)
        } catch (ex: Exception) {
            decryptedData
        }
    }


}