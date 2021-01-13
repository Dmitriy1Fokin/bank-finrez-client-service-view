package ru.fdo.bank.finrez.clientserviceview

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
open class ClientServiceViewApplication

fun main(args: Array<String>) {
    runApplication<ClientServiceViewApplication>(*args)
}
