create table tb_convenio
(
    id                 varchar(255) not null,
    created_at         datetime(6),
    updated_at         datetime(6),
    codigo_plano       integer,
    numero_carteirinha varchar(255) not null,
    operadora          smallint,
    registro_ans       varchar(255) not null,
    tipo_plano         smallint     not null,
    paciente_id        varchar(255),
    primary key (id)
);
