package com.example.usermanagementservice.infrastructure.security

import javax.crypto.SecretKey

/**
 * Provides a set of abstractions over the native java security api that meets the most common use cases.
 * @author Khiari Youssef
 * @since 27/01/2023
 */
interface EncryptionServices {


    /**
     * randomly generates a private key for AES symmetric encryption
     * @author Khiari Youssef
     * @since 27/01/2023
     */
    suspend fun generateSecretKey(): SecretKey


    /**
     * generates a secret for AES encryption based on a given password and salt using a password-based key derivation function internally
     * @author Khiari Youssef
     * @param password : password to encrypt the key
     * @param salt : salt to enhance the hashing algorithm's security against brute force attacks
     * @since 27/01/2023
     */
    suspend fun generateSecretKey(password: String, salt: String): SecretKey

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