CREATE TABLE IF NOT EXISTS `tb_convenio` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `numero_carteirinha` VARCHAR(255) NOT NULL,
    `registro_ans` VARCHAR(255),
    `operadora` INT,
    `tipo_plano` INT,
    `paciente_id` BIGINT,
    FOREIGN KEY (`paciente_id`) REFERENCES `tb_paciente`(`id`)
    );
