CREATE TABLE IF NOT EXISTS `test`.`computer_parts` (
    `id` BIGINT(20) NOT NULL,
    `quantity` INT(11) NOT NULL,
    `required` BIT(1) NOT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
