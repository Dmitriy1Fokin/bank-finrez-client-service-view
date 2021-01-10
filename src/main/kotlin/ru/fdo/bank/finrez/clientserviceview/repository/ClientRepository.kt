package ru.fdo.bank.finrez.clientserviceview.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.fdo.bank.finrez.clientserviceview.domain.Client
import ru.fdo.bank.finrez.clientserviceview.domain.TypeOfClient

interface ClientRepository : JpaRepository<Client, String>{
    fun findAllByTypeOfClient(typeOfClient: TypeOfClient, pageable: Pageable) : Page<Client>
}