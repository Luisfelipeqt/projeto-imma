create table tb_paciente
(
    id                varchar(255) not null,
    created_at        datetime(6),
    updated_at        datetime(6),
    cpf               varchar(14)  not null,
    data_nascimento   datetime(6),
    email             varchar(80),
    first_name        varchar(255),
    last_name         varchar(80)  not null,
    password          varchar(255) not null,
    telefone          varchar(15)  not null,
    convenio_saude_id varchar(255),
    primary key (id)
);