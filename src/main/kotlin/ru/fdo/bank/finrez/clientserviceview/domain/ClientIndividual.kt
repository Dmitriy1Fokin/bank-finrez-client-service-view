package ru.fdo.bank.finrez.clientserviceview.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ClientIndividual(@Column(name = "last_name", table = "client_individual") val lastName: String,
                            @Column(name = "first_name", table = "client_individual") val firstName: String,
                            @Column(name = "middle_name", table = "client_individual") val middleName: String,
                            @Column(name = "citizenship", table = "client_individual") val citizenship: String,
                            @Column(name = "birthday", table = "client_individual") val birthday: LocalDate,
                            @Column(name = "passport", table = "client_individual") val passport: String)