alter table tb_convenio
    add constraint FKiqsdtw7j4y2cmngpy3g7j02lx
        foreign key (paciente_id)
            references tb_paciente (id);