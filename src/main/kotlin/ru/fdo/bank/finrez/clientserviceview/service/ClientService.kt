package ru.fdo.bank.finrez.clientserviceview.service

import ru.fdo.bank.finrez.clientserviceview.domain.Client
import java.util.concurrent.CompletableFuture

interface ClientService {
    fun getClintById(clientId : String) : CompletableFuture<Client>
}