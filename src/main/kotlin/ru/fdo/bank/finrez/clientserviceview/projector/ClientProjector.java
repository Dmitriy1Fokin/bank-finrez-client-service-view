package ru.fdo.bank.finrez.clientserviceview.projector;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientCorpCreatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientCorpUpdatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByIdQuery;
import ru.fdo.bank.finrez.clientserviceview.domain.Client;
import ru.fdo.bank.finrez.clientserviceview.domain.ClientCorp;
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient;
import ru.fdo.bank.finrez.clientserviceview.repository.ClientRepository;


@Component
public class ClientProjector {

    private final ClientRepository clientRepository;

    public ClientProjector(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @EventHandler
    public void on(ClientCorpCreatedEvent event){
        saveClientCorp(event.getName(),
                event.getOrgForm(),
                event.getAbbreviatedName(),
                event.getInn(),
                event.getKpp(),
                event.getOgrn(),
                event.getOkpo(),
                event.getOkved(),
                event.getAddressU(),
                event.getAddressF(),
                event.getEmail(),
                event.getClientId());
    }

    @Transactional
    @EventHandler
    public void on(ClientCorpUpdatedEvent event){
        saveClientCorp(event.getName(),
                event.getOrgForm(),
                event.getAbbreviatedName(),
                event.getInn(),
                event.getKpp(),
                event.getOgrn(),
                event.getOkpo(),
                event.getOkved(),
                event.getAddressU(),
                event.getAddressF(),
                event.getEmail(),
                event.getClientId());
    }

    @Transactional
    public void saveClientCorp(String name,
                               String orgForm,
                               String abbreviatedName,
                               String inn,
                               String kpp,
                               String ogrn,
                               String okpo,
                               String okved,
                               String addressU,
                               String addressF,
                               String email,
                               String clientId) {
        final ClientCorp clientCorp = new ClientCorp(name,
                orgForm,
                abbreviatedName,
                inn,
                kpp,
                ogrn,
                okpo,
                okved,
                addressU,
                addressF,
                email);
        final Client client = new Client(clientId, TypeOfClient.CORP, clientCorp, null);
        clientRepository.save(client);
    }

    @QueryHandler
    public Client handle(FindClientByIdQuery query){
        return clientRepository.findById(query.getClientId()).orElse(null);
    }
}
