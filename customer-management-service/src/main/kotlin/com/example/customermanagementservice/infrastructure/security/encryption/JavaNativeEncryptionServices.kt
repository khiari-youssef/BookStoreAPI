package com.example.customermanagementservice.infrastructure.security.encryption

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey


@Service
class JavaNativeEncryptionServices : EncryptionServices {

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default

    private val symmetricEncryptionAlgorithm: String = "AES"


    override suspend fun encrypt(
        inputData: ByteArray,
        secretKey: SecretKey,
        encodeBase64: Boolean
    ): ByteArray = withContext(coroutineDispatcher) {
        val cipher = Cipher.getInstance(symmetricEncryptionAlgorithm)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        cipher.doFinal( if(encodeBase64) Base64.getEncoder().encode(inputData) else inputData)
    }


    override suspend fun decrypt(
        encryptedData: ByteArray,
        secretKey: SecretKey,
        decodeBase64: Boolean
    ): ByteArray = withContext(coroutineDispatcher) {
        val cipher = Cipher.getInstance(symmetricEncryptionAlgorithm)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedData = cipher.doFinal(encryptedData)
        try {
            Base64.getDecoder().decode(decryptedData)
        } catch (ex: Exception) {
            decryptedData
        }
    }


}