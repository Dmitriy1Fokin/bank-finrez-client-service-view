package ru.fdo.bank.finrez.clientserviceview.projector;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientCorpCreatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientCorpUpdatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientIndividualCreatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.event.ClientIndividualUpdatedEvent;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllClientQuery;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllCorpClientQuery;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindAllIndividualClientQuery;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByIdQuery;
import ru.fdo.bank.finrez.clientservicecommon.coreapi.query.FindClientByParamsQuery;
import ru.fdo.bank.finrez.clientserviceview.domain.Client;
import ru.fdo.bank.finrez.clientserviceview.domain.ClientCorp;
import ru.fdo.bank.finrez.clientserviceview.domain.ClientIndividual;
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient;
import ru.fdo.bank.finrez.clientserviceview.repository.ClientRepository;
import ru.fdo.bank.finrez.clientserviceview.search.Search;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ClientProjector {

    private final ClientRepository clientRepository;

    public ClientProjector(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @EventHandler
    public void on(ClientCorpCreatedEvent event){
        log.debug("triggered ClientCorpCreatedEvent: {}", event);
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
        log.debug("triggered ClientCorpUpdatedEvent: {}", event);
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
    public void on(ClientIndividualCreatedEvent event){
        log.debug("triggered ClientIndividualCreatedEvent: {}", event);
        saveClientIndividual(event.getClientId(),
                event.getLastName(),
                event.getFirstName(),
                event.getMiddleName(),
                event.getCitizenship(),
                event.getBirthday(),
                event.getPassport());
    }

    @Transactional
    @EventHandler
    public void on(ClientIndividualUpdatedEvent event){
        log.debug("triggered ClientIndividualUpdatedEvent: {}", event);
        saveClientIndividual(event.getClientId(),
                event.getLastName(),
                event.getFirstName(),
                event.getMiddleName(),
                event.getCitizenship(),
                event.getBirthday(),
                event.getPassport());
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

    @Transactional
    public void saveClientIndividual(String clientId,
                                     String lastName,
                                     String firstName,
                                     String middleName,
                                     String citizenship,
                                     LocalDate birthday,
                                     String passport) {
        final ClientIndividual clientIndividual = new ClientIndividual(lastName,
                firstName,
                middleName,
                citizenship,
                birthday,
                passport);
        final Client client = new Client(clientId, TypeOfClient.INDIVIDUAL, null, clientIndividual);
        clientRepository.save(client);
    }

    @QueryHandler
    public Client handle(FindClientByIdQuery query){
        log.debug("triggered FindClientByIdQuery: {}", query);
        return clientRepository.findById(query.getClientId()).orElse(null);
    }

    @QueryHandler
    public List<Client> handle(FindAllClientQuery query){
        log.debug("triggered FindAllClientQuery: {}", query);
        return clientRepository.findAll(PageRequest.of(query.getPage(), query.getSize())).getContent();
    }

    @QueryHandler
    public List<Client> handle(FindAllCorpClientQuery query){
        log.debug("triggered FindAllCorpClientQuery: {}", query);
        return clientRepository.findAllByTypeOfClient(TypeOfClient.CORP, PageRequest.of(query.getPage(), query.getSize())).getContent();
    }

    @QueryHandler
    public List<Client> handle(FindAllIndividualClientQuery query){
        log.debug("triggered FindAllIndividualClientQuery: {}", query);
        return clientRepository.findAllByTypeOfClient(TypeOfClient.INDIVIDUAL, PageRequest.of(query.getPage(), query.getSize())).getContent();
    }

    @QueryHandler
    public List<Client> handle(FindClientByParamsQuery query) throws ReflectiveOperationException {
        log.debug("triggered FindClientByParamsQuery: {}", query);

        final String SEARCH_PARAM_TYPE_OF_CLIENT = "typeOfClient";
        final String SEARCH_PARAM_PAGE = "page";
        final String SEARCH_PARAM_SIZE = "size";
        final Map<String, String> searchParam = query.getSearchParameters();

        Search<Client> clientSearch = new Search<>(Client.class);

        clientSearch.withParam(searchParam);

        if(!StringUtils.isEmpty(searchParam.get(SEARCH_PARAM_TYPE_OF_CLIENT))){
            if(searchParam.get(SEARCH_PARAM_TYPE_OF_CLIENT).equals(TypeOfClient.CORP.name())){
                clientSearch.withNestedAttributeParam(searchParam, "clientCorp");
            }else if(searchParam.get(SEARCH_PARAM_TYPE_OF_CLIENT).equals(TypeOfClient.INDIVIDUAL.name())){
                clientSearch.withNestedAttributeParam(searchParam, "clientIndividual");
            }
        }else {
            return Collections.emptyList();
        }

        Specification<Client> specification = clientSearch.getSpecification();

        final int page;
        if(StringUtils.isEmpty(searchParam.get(SEARCH_PARAM_PAGE))){
            page = 0;
        }else {
            page = Integer.parseInt(searchParam.get(SEARCH_PARAM_PAGE));
        }

        final int size;
        if(StringUtils.isEmpty(searchParam.get(SEARCH_PARAM_SIZE))){
            size = 10;
        }else {
            size = Integer.parseInt(searchParam.get(SEARCH_PARAM_SIZE));
        }
        Pageable pageable = PageRequest.of(page, size);

        return clientRepository.findAll(specification, pageable).getContent();
    }
}
