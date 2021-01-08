package ru.fdo.bank.finrez.clientserviceview.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ClientCorp(@Column(name = "name", table = "client_corp") val name: String,
                      @Column(name = "org_form", table = "client_corp")val orgForm: String,
                      @Column(name = "abbreviated_name", table = "client_corp")val abbreviatedName: String,
                      @Column(name = "inn", table = "client_corp")val inn: String,
                      @Column(name = "kpp", table = "client_corp")val kpp: String,
                      @Column(name = "ogrn", table = "client_corp")val ogrn: String,
                      @Column(name = "okpo", table = "client_corp")val okpo: String,
                      @Column(name = "okved", table = "client_corp")val okved: String,
                      @Column(name = "address_u", table = "client_corp")val addressU: String,
                      @Column(name = "address_f", table = "client_corp")val addressF: String,
                      @Column(name = "email", table = "client_corp")val email: String)