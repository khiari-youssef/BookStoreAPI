package com.example.customermanagementservice.infrastructure.security.integrity

import java.security.PrivateKey
import java.security.PublicKey

interface DataIntegrityServices {

    companion object{
        private const val DigitalSignatureAlgorithmSuite : String = "SHA256withECDSA"
    }

suspend  fun signData(data : ByteArray,privateKey: PrivateKey,algorithm : String = DigitalSignatureAlgorithmSuite) : ByteArray

suspend  fun verifyData(data : ByteArray,publicKey: PublicKey,signature : ByteArray,algorithm : String = DigitalSignatureAlgorithmSuite) : Boolean

suspend fun  pbkdf2(data: CharArray,iterations : Int = 1000,salt : ByteArray,keyLength : Int = 256) : ByteArray


}