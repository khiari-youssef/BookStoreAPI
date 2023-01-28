package com.example.bookmanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookManagementServiceApplication

fun main(args: Array<String>) {
    runApplication<BookManagementServiceApplication>(*args)
}
