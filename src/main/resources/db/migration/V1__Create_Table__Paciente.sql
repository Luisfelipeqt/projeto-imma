CREATE TABLE IF NOT EXISTS `tb_paciente` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(80) NOT NULL,
    `last_name` varchar(80) NOT NULL,
    `cpf` varchar(14) NOT NULL,
    `data_nascimento` date NOT NULL,
    `telefone` varchar(15) NOT NULL,
    `email` varchar(255) NOT NULL,
    `convenio_saude_id` BIGINT(20),
    PRIMARY KEY (id)
    );