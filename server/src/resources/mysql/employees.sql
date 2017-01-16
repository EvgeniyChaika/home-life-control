CREATE TABLE `home-life-control`.`employees` (
  `id_employee` BIGINT NOT NULL AUTO_INCREMENT,
  `employee_name` VARCHAR(45) NOT NULL,
  `employee_email` VARCHAR(45) NOT NULL,
  `employee_salary` INT NOT NULL,
  `employee_register` DATE NOT NULL,
  `id_department` BIGINT NOT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE INDEX `employee_email_UNIQUE` (`employee_email` ASC),
  INDEX `id_department_idx` (`id_department` ASC),
  CONSTRAINT `id_department`
  FOREIGN KEY (`id_department`)
  REFERENCES `home-life-control`.`departments` (`id_department`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);