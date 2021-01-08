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

--Axon framework table
CREATE TABLE public.association_value_entry (
    id int8 NOT NULL,
    association_key varchar(255) NOT NULL,
    association_value varchar(255) NULL,
    saga_id varchar(255) NOT NULL,
    saga_type varchar(255) NULL,
    CONSTRAINT association_value_entry_pkey PRIMARY KEY (id)
);
CREATE INDEX idxgv5k1v2mh6frxuy5c0hgbau94 ON public.association_value_entry USING btree (saga_id, saga_type);
CREATE INDEX idxk45eqnxkgd8hpdn6xixn8sgft ON public.association_value_entry USING btree (saga_type, association_key, association_value);

--Axon framework table
CREATE TABLE public.saga_entry (
    saga_id varchar(255) NOT NULL,
    revision varchar(255) NULL,
    saga_type varchar(255) NULL,
    serialized_saga oid NULL,
    CONSTRAINT saga_entry_pkey PRIMARY KEY (saga_id)
);

--Axon framework table
CREATE TABLE public.token_entry (
    processor_name varchar(255) NOT NULL,
    segment int4 NOT NULL,
    "owner" varchar(255) NULL,
    "timestamp" varchar(255) NOT NULL,
    "token" oid NULL,
    token_type varchar(255) NULL,
    CONSTRAINT token_entry_pkey PRIMARY KEY (processor_name, segment)
);
