alter table tb_paciente
    add constraint FKclranp7if38airogpal28dtq3
        foreign key (convenio_saude_id)
            references tb_convenio (id);