alter table tb_consulta
    add constraint FKciauiqnfm67ea65tjfjj91ev2
        foreign key (paciente_id)
            references tb_paciente (id);