-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fabric
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fabric` ;

-- -----------------------------------------------------
-- Schema fabric
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fabric` DEFAULT CHARACTER SET utf8 ;
USE `fabric` ;

-- -----------------------------------------------------
-- Table `fabric`.`role_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`role_application` (
  `id_role_application` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `level` INT(11) NOT NULL,
  PRIMARY KEY (`id_role_application`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `level_UNIQUE` (`level` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fabric`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(60) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `status` INT(1) NULL DEFAULT NULL,
  `id_role_application` INT(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_role_application` (`id_role_application` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_application`
    FOREIGN KEY (`id_role_application`)
    REFERENCES `fabric`.`role_application` (`id_role_application`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fabric`.`producer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`producer` (
  `id_producer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_producer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`component_parts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`component_parts` (
  `id_component_parts` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `cost` DOUBLE NOT NULL,
  `producer_id_producer` INT NOT NULL,
  PRIMARY KEY (`id_component_parts`),
  UNIQUE INDEX `id_component_parts_UNIQUE` (`id_component_parts` ASC) VISIBLE,
  INDEX `fk_component_parts_producer1_idx` (`producer_id_producer` ASC) VISIBLE,
  CONSTRAINT `fk_component_parts_producer1`
    FOREIGN KEY (`producer_id_producer`)
    REFERENCES `fabric`.`producer` (`id_producer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`production`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`production` (
  `id_production` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(70) NOT NULL,
  `describe` VARCHAR(100) NULL,
  PRIMARY KEY (`id_production`),
  UNIQUE INDEX `id_production_UNIQUE` (`id_production` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`component_parts_has_production`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`component_parts_has_production` (
  `id_component_parts_has_production` INT NOT NULL AUTO_INCREMENT,
  `component_parts_id_component_parts` INT NOT NULL,
  `production_id_production` INT NOT NULL,
  PRIMARY KEY (`id_component_parts_has_production`, `component_parts_id_component_parts`, `production_id_production`),
  INDEX `fk_component_parts_has_production_production1_idx` (`production_id_production` ASC) VISIBLE,
  INDEX `fk_component_parts_has_production_component_parts1_idx` (`component_parts_id_component_parts` ASC) VISIBLE,
  UNIQUE INDEX `id_component_parts_has_production_UNIQUE` (`id_component_parts_has_production` ASC) VISIBLE,
  CONSTRAINT `fk_component_parts_has_production_component_parts1`
    FOREIGN KEY (`component_parts_id_component_parts`)
    REFERENCES `fabric`.`component_parts` (`id_component_parts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_component_parts_has_production_production1`
    FOREIGN KEY (`production_id_production`)
    REFERENCES `fabric`.`production` (`id_production`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`outgoing_const`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`outgoing_const` (
  `id_outgoing_const` INT NOT NULL AUTO_INCREMENT,
  `outgoing_name` VARCHAR(100) NOT NULL,
  `cost` DOUBLE NOT NULL,
  PRIMARY KEY (`id_outgoing_const`),
  UNIQUE INDEX `id_outgoing_const_UNIQUE` (`id_outgoing_const` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`outgoing_dynamic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`outgoing_dynamic` (
  `id_outgoing_dynamic` INT NOT NULL AUTO_INCREMENT,
  `outgoing_name` VARCHAR(100) NOT NULL,
  `cost` DOUBLE NOT NULL,
  PRIMARY KEY (`id_outgoing_dynamic`),
  UNIQUE INDEX `id_outgoing_dynamic_UNIQUE` (`id_outgoing_dynamic` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`production_has_outgoing_dynamic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`production_has_outgoing_dynamic` (
  `id_production_has_outgoing_dynamic` INT NOT NULL AUTO_INCREMENT,
  `production_id_production` INT NOT NULL,
  `outgoing_dynamic_id_outgoing_dynamic` INT NOT NULL,
  PRIMARY KEY (`id_production_has_outgoing_dynamic`, `production_id_production`, `outgoing_dynamic_id_outgoing_dynamic`),
  INDEX `fk_production_has_outgoing_dynamic_outgoing_dynamic1_idx` (`outgoing_dynamic_id_outgoing_dynamic` ASC) VISIBLE,
  INDEX `fk_production_has_outgoing_dynamic_production1_idx` (`production_id_production` ASC) VISIBLE,
  UNIQUE INDEX `id_production_has_outgoing_dynamic_UNIQUE` (`id_production_has_outgoing_dynamic` ASC) VISIBLE,
  CONSTRAINT `fk_production_has_outgoing_dynamic_production1`
    FOREIGN KEY (`production_id_production`)
    REFERENCES `fabric`.`production` (`id_production`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_production_has_outgoing_dynamic_outgoing_dynamic1`
    FOREIGN KEY (`outgoing_dynamic_id_outgoing_dynamic`)
    REFERENCES `fabric`.`outgoing_dynamic` (`id_outgoing_dynamic`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`production_has_outgoing_const`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`production_has_outgoing_const` (
  `id_production_has_outgoing_const` INT NOT NULL AUTO_INCREMENT,
  `production_id_production` INT NOT NULL,
  `outgoing_const_id_outgoing_const` INT NOT NULL,
  PRIMARY KEY (`id_production_has_outgoing_const`, `production_id_production`, `outgoing_const_id_outgoing_const`),
  INDEX `fk_production_has_outgoing_const_outgoing_const1_idx` (`outgoing_const_id_outgoing_const` ASC) VISIBLE,
  INDEX `fk_production_has_outgoing_const_production1_idx` (`production_id_production` ASC) VISIBLE,
  UNIQUE INDEX `id_production_has_outgoing_const_UNIQUE` (`id_production_has_outgoing_const` ASC) VISIBLE,
  CONSTRAINT `fk_production_has_outgoing_const_production1`
    FOREIGN KEY (`production_id_production`)
    REFERENCES `fabric`.`production` (`id_production`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_production_has_outgoing_const_outgoing_const1`
    FOREIGN KEY (`outgoing_const_id_outgoing_const`)
    REFERENCES `fabric`.`outgoing_const` (`id_outgoing_const`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`consumer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`consumer` (
  `id_consumer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_consumer`),
  UNIQUE INDEX `id_consumer_UNIQUE` (`id_consumer` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`booking` (
  `id_booking` INT NOT NULL AUTO_INCREMENT,
  `name_booking` VARCHAR(45) NOT NULL,
  `consumer_id_consumer` INT NOT NULL,
  `coef_multiply` DOUBLE NOT NULL,
  PRIMARY KEY (`id_booking`, `consumer_id_consumer`),
  UNIQUE INDEX `id_booking_UNIQUE` (`id_booking` ASC) VISIBLE,
  INDEX `fk_booking_consumer1_idx` (`consumer_id_consumer` ASC) VISIBLE,
  CONSTRAINT `fk_booking_consumer1`
    FOREIGN KEY (`consumer_id_consumer`)
    REFERENCES `fabric`.`consumer` (`id_consumer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fabric`.`production_has_booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fabric`.`production_has_booking` (
  `id_production_has_booking` INT NOT NULL AUTO_INCREMENT,
  `production_id_production` INT NOT NULL,
  `booking_id_booking` INT NOT NULL,
  PRIMARY KEY (`id_production_has_booking`, `production_id_production`, `booking_id_booking`),
  INDEX `fk_production_has_booking_booking1_idx` (`booking_id_booking` ASC) VISIBLE,
  INDEX `fk_production_has_booking_production1_idx` (`production_id_production` ASC) VISIBLE,
  UNIQUE INDEX `id_production_has_booking_UNIQUE` (`id_production_has_booking` ASC) VISIBLE,
  CONSTRAINT `fk_production_has_booking_production1`
    FOREIGN KEY (`production_id_production`)
    REFERENCES `fabric`.`production` (`id_production`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_production_has_booking_booking1`
    FOREIGN KEY (`booking_id_booking`)
    REFERENCES `fabric`.`booking` (`id_booking`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
