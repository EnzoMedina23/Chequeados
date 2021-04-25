

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema chequeadostp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chequeadostp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chequeadostp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `chequeadostp` ;

-- -----------------------------------------------------
-- Table `chequeadostp`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chequeadostp`.`categoria` (
  `idCategoria` VARCHAR(45) NOT NULL,
  `comentario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chequeadostp`.`explicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chequeadostp`.`explicacion` (
  `idExplicacion` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL DEFAULT NULL,
  `epigrafe` VARCHAR(45) NULL DEFAULT NULL,
  `contenido` VARCHAR(45) NULL DEFAULT NULL,
  `fechaCreacion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idExplicacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chequeadostp`.`chequeos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chequeadostp`.`chequeos` (
  `idChequeos` INT(11) NOT NULL AUTO_INCREMENT,
  `palabraClave` VARCHAR(45) NULL DEFAULT NULL,
  `frase` VARCHAR(45) NULL DEFAULT NULL,
  `medioPublicacion` VARCHAR(45) NULL DEFAULT NULL,
  `enlace` VARCHAR(45) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `estado` TINYINT(4) NULL DEFAULT NULL,
  `idCategoria` VARCHAR(45) NULL DEFAULT NULL,
  `idExplicacion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idChequeos`),
  INDEX `fkExpli_idx` (`idExplicacion` ASC) VISIBLE,
  CONSTRAINT `fkExpli`
    FOREIGN KEY (`idExplicacion`)
    REFERENCES `chequeadostp`.`explicacion` (`idExplicacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chequeadostp`.`investigacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chequeadostp`.`investigacion` (
  `idInvestigacion` INT(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` VARCHAR(45) NULL DEFAULT NULL,
  `tema` VARCHAR(45) NULL DEFAULT NULL,
  `palabrasClaves` VARCHAR(45) NULL DEFAULT NULL,
  `titulo` VARCHAR(45) NULL DEFAULT NULL,
  `epigrafe` VARCHAR(45) NULL DEFAULT NULL,
  `contenido` VARCHAR(45) NULL DEFAULT NULL,
  `fechaCreacion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idInvestigacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chequeadostp`.`repositorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chequeadostp`.`repositorio` (
  `idSugerencia` INT(11) NOT NULL AUTO_INCREMENT,
  `frase` VARCHAR(45) NULL DEFAULT NULL,
  `medioPublicacion` VARCHAR(45) NULL DEFAULT NULL,
  `enlace` VARCHAR(45) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idSugerencia`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
