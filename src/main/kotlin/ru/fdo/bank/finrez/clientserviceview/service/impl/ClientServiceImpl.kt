package ru.fdo.bank.finrez.clientserviceview.service.impl

import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllClientQuery
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllCorpClientQuery
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllIndividualClientQuery
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByIdQuery
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByParamsQuery
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient
import ru.fdo.bank.finrez.clientserviceview.service.ClientService
import java.util.concurrent.CompletableFuture

@Service
class ClientServiceImpl(private val queryGateway: QueryGateway) : ClientService{

    private val logger : Logger = LoggerFactory.getLogger(ClientServiceImpl::class.java)

    override fun getClintById(clientId: String): CompletableFuture<Client> {
        logger.debug("triggered getClintById: clientId=$clientId")
        return queryGateway.query(FindClientByIdQuery(clientId), ResponseTypes.instanceOf(Client::class.java))
    }

    override fun getAllClients(page: Int, size: Int): CompletableFuture<List<Client>> {
        logger.debug("triggered getAllClients: page=$page, size=$size")
        return queryGateway.query(FindAllClientQuery(size, page), ResponseTypes.multipleInstancesOf(Client::class.java))
    }

    override fun getAllClientsByType(typeOfClient: TypeOfClient, page: Int, size: Int): CompletableFuture<List<Client>> {
        logger.debug("triggered getAllClientsByType: typeOfClient=$typeOfClient, page=$page, size=$size")
        return if (typeOfClient == TypeOfClient.CORP)
            queryGateway.query(FindAllCorpClientQuery(size, page), ResponseTypes.multipleInstancesOf(Client::class.java))
        else
            queryGateway.query(FindAllIndividualClientQuery(size, page), ResponseTypes.multipleInstancesOf(Client::class.java))
    }

    override fun getClientsByParams(searchParameters: Map<String, String>): CompletableFuture<List<Client>> {
        logger.debug("triggered getClientsByParams: searchParameters=$searchParameters")
        return queryGateway.query(FindClientByParamsQuery(searchParameters), ResponseTypes.multipleInstancesOf(Client::class.java))
    }
}