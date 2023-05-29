create table tb_consulta
(
    id          varchar(255) not null,
    created_at  datetime(6),
    updated_at  datetime(6),
    data        datetime(6),
    medico_id   varchar(255),
    paciente_id varchar(255),
    primary key (id)
);