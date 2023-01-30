package com.example.springboottraining

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class BookStoreAPIApplication

fun main(args: Array<String>) {
    runApplication<BookStoreAPIApplication>(*args)
}

