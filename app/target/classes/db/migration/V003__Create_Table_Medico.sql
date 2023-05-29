create table tb_medico
(
    id                   varchar(255) not null,
    created_at           datetime(6),
    updated_at           datetime(6),
    ativo                bit,
    cpf                  varchar(14)  not null,
    crm                  varchar(255),
    email                varchar(255),
    especialidade_medica smallint,
    first_name           varchar(255) not null,
    last_name            varchar(255) not null,
    telefone             varchar(15)  not null,
    primary key (id)
);