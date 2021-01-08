package ru.fdo.bank.finrez.clientserviceview.domain

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.SecondaryTable
import javax.persistence.SecondaryTables
import javax.persistence.Table

@Entity
@Table(name = "client_prime")
@SecondaryTables(SecondaryTable(name = "client_individual", pkJoinColumns = arrayOf(PrimaryKeyJoinColumn(name = "client_id"))),
        SecondaryTable(name = "client_corp", pkJoinColumns = arrayOf(PrimaryKeyJoinColumn(name = "client_id"))))
data class Client(@Id @Column(name = "client_id") val clientId : String,
                  @Column(name = "type_of_client") @Enumerated(EnumType.STRING) val typeOfClient : TypeOfClient,
                  @Embedded val clientCorp: ClientCorp?,
                  @Embedded val clientIndividual: ClientIndividual?)