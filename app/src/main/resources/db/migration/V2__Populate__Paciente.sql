-- Nome: V2__insert_tb_paciente
-- Versão: 2
-- Descrição: Insere alguns dados iniciais na tabela tb_paciente
INSERT INTO `tb_paciente` (
                           `first_name`,
                           `last_name`,
                           `cpf`,
                           `data_nascimento`,
                           `telefone`,
                           `email`,
                           `convenio_saude_id`
)
VALUES
    ('João', 'Silva', '123.456.789-00', '1990-01-01', '(11) 9999-8888', 'joao.silva@email.com', 2),
    ('Maria', 'Souza', '987.654.321-00', '1995-02-15', '(11) 8888-7777', 'maria.souza@email.com', 2);
