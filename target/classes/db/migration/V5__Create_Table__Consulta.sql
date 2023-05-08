
CREATE TABLE tb_consulta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data TIMESTAMP NOT NULL,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES tb_medico(id),
    FOREIGN KEY (paciente_id) REFERENCES tb_paciente(id)
);
