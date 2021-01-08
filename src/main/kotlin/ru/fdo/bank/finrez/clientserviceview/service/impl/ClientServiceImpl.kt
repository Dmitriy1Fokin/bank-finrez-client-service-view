package ru.fdo.bank.finrez.clientserviceview.service.impl

import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByIdQuery
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.service.ClientService
import java.util.concurrent.CompletableFuture

@Service
class ClientServiceImpl(private val queryGateway: QueryGateway) : ClientService{

    override fun getClintById(clientId: String): CompletableFuture<Client> =
        queryGateway.query(FindClientByIdQuery(clientId), ResponseTypes.instanceOf(Client::class.java))

}