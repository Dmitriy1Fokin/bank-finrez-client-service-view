CREATE TABLE public.client_prime(
    client_id character varying PRIMARY KEY,
    type_of_client character varying NOT NULL
);

CREATE TABLE public.client_corp (
    client_id character varying PRIMARY KEY,
    name character varying NOT NULL,
    org_form character varying NOT NULL,
    abbreviated_name character varying NOT NULL,
    INN character varying NOT NULL,
    KPP character varying NOT NULL,
    OGRN character varying NOT NULL,
    OKPO character varying NOT NULL,
    OKVED character varying NOT NULL,
    address_u character varying NOT NULL,
    address_f character varying NOT NULL,
    email character varying NOT NULL
);

CREATE TABLE public.client_individual(
    client_id character varying PRIMARY KEY,
    last_name character varying NOT NULL,
    first_name character varying NOT NULL,
    middle_name character varying NOT NULL,
    citizenship character varying NOT NULL,
    birthday date not null,
    passport character varying NOT NULL
);

ALTER TABLE ONLY public.client_corp
    ADD CONSTRAINT fk_client_corp_client_prime FOREIGN KEY (client_id) REFERENCES public.client_prime(client_id);

ALTER TABLE ONLY public.client_individual
    ADD CONSTRAINT fk_client_individual_client_prime FOREIGN KEY (client_id) REFERENCES public.client_prime(client_id);