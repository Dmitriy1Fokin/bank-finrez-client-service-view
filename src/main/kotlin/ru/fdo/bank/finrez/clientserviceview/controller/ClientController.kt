package ru.fdo.bank.finrez.clientserviceview.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.domain.ClientCorp
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient
import ru.fdo.bank.finrez.clientserviceview.repository.ClientRepository
import ru.fdo.bank.finrez.clientserviceview.service.ClientService
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/client/view")
class ClientController(private val clientService: ClientService){

    @GetMapping("/{clientId}")
    fun getClient(@PathVariable("clientId") clientId: String) : CompletableFuture<Client> =
            clientService.getClintById(clientId)

}