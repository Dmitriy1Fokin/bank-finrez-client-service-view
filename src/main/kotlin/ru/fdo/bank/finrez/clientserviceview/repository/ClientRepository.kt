package ru.fdo.bank.finrez.clientserviceview.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.fdo.bank.finrez.clientserviceview.domain.Client

interface ClientRepository : JpaRepository<Client, String>