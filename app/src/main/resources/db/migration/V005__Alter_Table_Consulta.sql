ALTER TABLE tb_consulta
    ADD CONSTRAINT FKg00bpyhie7hvd6v28gmjb4unx
        FOREIGN KEY (medico_id)
            REFERENCES tb_medico (id);
