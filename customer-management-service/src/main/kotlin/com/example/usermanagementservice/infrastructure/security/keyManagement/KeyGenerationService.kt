package com.example.usermanagementservice.infrastructure.security.keyManagement

import java.security.KeyPair
import javax.crypto.SecretKey

interface KeyGenerationService {

    companion object{
       const val Symmetric_Encryption_Algorithm: String = "AES"
        const val Asymmetric_Encryption_Algorithm = "EC"
    }

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
     * Randomly generates a pair of private and public key using elliptic curve cryptography algorithm
     * @author Khiari Youssef
     * @return KeyPair
     */
    suspend fun generateKeyPair() : KeyPair


}