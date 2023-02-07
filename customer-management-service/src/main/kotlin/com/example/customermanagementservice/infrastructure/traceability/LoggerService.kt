package com.example.customermanagementservice.infrastructure.traceability

interface LoggerService {

    fun logE(tag : String,message : String)

    fun logI(tag : String,message : String)

    fun logW(tag : String,message : String)

    fun log(tag : String,message : String)
}