package ru.fdo.bank.finrez.clientserviceview.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient
import ru.fdo.bank.finrez.clientserviceview.service.ClientService
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/client/view")
class ClientController(private val clientService: ClientService){

    @GetMapping("/{clientId}")
    fun getClient(@PathVariable("clientId") clientId: String) : CompletableFuture<Client> =
            clientService.getClintById(clientId)

    @GetMapping("/all")
    fun getAllClients(@RequestParam("page") page: Int,
                      @RequestParam("size") size: Int) : CompletableFuture<List<Client>> =
            clientService.getAllClients(page, size)

    @GetMapping("/type")
    fun getAllClientsByType(@RequestParam("typeOfClient") typeOfClient: TypeOfClient,
                            @RequestParam("page") page: Int,
                            @RequestParam("size") size: Int) : CompletableFuture<List<Client>> =
            clientService.getAllClientsByType(typeOfClient, page, size)

    @GetMapping("/search")
    fun getClientsByParams(@RequestParam searchParams: Map<String, String>) : CompletableFuture<List<Client>> =
            clientService.getClientsByParams(searchParams)

}