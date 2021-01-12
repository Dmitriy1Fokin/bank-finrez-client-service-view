package ru.fdo.bank.finrez.clientserviceview.service

import org.springframework.data.domain.Pageable
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient
import java.util.concurrent.CompletableFuture

interface ClientService {
    fun getClintById(clientId : String) : CompletableFuture<Client>
    fun getAllClients(page: Int, size: Int) : CompletableFuture<List<Client>>
    fun getAllClientsByType(typeOfClient: TypeOfClient, page: Int, size: Int) : CompletableFuture<List<Client>>
    fun getClientsByParams(searchParameters : Map<String, String>, pageable: Pageable) : CompletableFuture<List<Client>>
}