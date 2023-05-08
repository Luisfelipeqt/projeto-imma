CREATE TABLE IF NOT EXISTS `tb_medico` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `cpf` VARCHAR(14) NOT NULL,
    `crm` VARCHAR(20) NOT NULL,
    `telefone` VARCHAR(15) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `especialidade_medica` INT(11) NOT NULL,
    `ativo` BIT NOT NULL,
    PRIMARY KEY (`id`)
    );
