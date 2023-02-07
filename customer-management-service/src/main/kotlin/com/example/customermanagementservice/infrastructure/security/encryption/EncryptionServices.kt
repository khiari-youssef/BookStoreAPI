package com.example.customermanagementservice.infrastructure.security.encryption

import javax.crypto.SecretKey

/**
 * Provides a set of abstractions over the native java security api that meets the most common use cases.
 * @author Khiari Youssef
 * @since 27/01/2023
 */
interface EncryptionServices {




    /**
     * encrypts plain text data using aes algorithm using a secret key (and base64 encoding if specified)
     * @author Khiari Youssef
     * @param inputData : plain text data
     * @param secretKey : encryption secret key
     * @param encodeBase64 : true if plain text should be encoded to base64 before encryption
     * @since 27/01/2023
     */
    suspend fun encrypt(
        inputData: ByteArray,
        secretKey: SecretKey,
        encodeBase64: Boolean = false
    ): ByteArray


    /**
     * decrypts data using a secret key
     * @author Khiari Youssef
     * @param encryptedData : encrypted data
     * @param secretKey : decryption secret key
     * @param decodeBase64 : true if encrypted text should be decoded back from base64 to utf-8 after encryption (assuming its is already base64 otherwise it will return the result directly)
     * @since 27/01/2023
     * */
    suspend fun decrypt(
        encryptedData: ByteArray,
        secretKey: SecretKey,
        decodeBase64: Boolean = false
    ): ByteArray


}