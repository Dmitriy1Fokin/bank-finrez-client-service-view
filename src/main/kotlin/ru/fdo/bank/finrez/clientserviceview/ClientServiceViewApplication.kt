package ru.fdo.bank.finrez.clientserviceview

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ClientServiceViewApplication

fun main(args: Array<String>) {
    runApplication<ClientServiceViewApplication>(*args)
}
