-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema khomiakov_ip22_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema khomiakov_ip22_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `khomiakov_ip22_db` DEFAULT CHARACTER SET utf8 ;
USE `khomiakov_ip22_db` ;

-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`coach` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`muscle_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`muscle_groups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`exersices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`exersices` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `targeted_bodypart` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `targeted_bodypart_UNIQUE` (`targeted_bodypart` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  CONSTRAINT `fk_Exersices_Muscle_groups1`
    FOREIGN KEY (`targeted_bodypart`)
    REFERENCES `khomiakov_ip22_db`.`muscle_groups` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`programs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`programs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`exersices_has_programs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`exersices_has_programs` (
  `Exersices_idExersices` INT NOT NULL,
  `Programs_id` INT UNSIGNED NOT NULL,
  `exercise_duration` TIME NULL DEFAULT NULL,
  `number_of_repetitions` INT NULL DEFAULT NULL,
  `number_of_sets` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Exersices_idExersices`, `Programs_id`),
  INDEX `fk_Exersices_has_Programs_Programs1_idx` (`Programs_id` ASC) VISIBLE,
  INDEX `fk_Exersices_has_Programs_Exersices_idx` (`Exersices_idExersices` ASC) VISIBLE,
  CONSTRAINT `fk_Exersices_has_Programs_Exersices`
    FOREIGN KEY (`Exersices_idExersices`)
    REFERENCES `khomiakov_ip22_db`.`exersices` (`id`),
  CONSTRAINT `fk_Exersices_has_Programs_Programs1`
    FOREIGN KEY (`Programs_id`)
    REFERENCES `khomiakov_ip22_db`.`programs` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`programs_has_coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`programs_has_coach` (
  `Programs_id` INT UNSIGNED NOT NULL,
  `Coach_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Programs_id`, `Coach_id`),
  INDEX `fk_Programs_has_Coach_Coach1_idx` (`Coach_id` ASC) VISIBLE,
  INDEX `fk_Programs_has_Coach_Programs1_idx` (`Programs_id` ASC) VISIBLE,
  CONSTRAINT `fk_Programs_has_Coach_Coach1`
    FOREIGN KEY (`Coach_id`)
    REFERENCES `khomiakov_ip22_db`.`coach` (`id`),
  CONSTRAINT `fk_Programs_has_Coach_Programs1`
    FOREIGN KEY (`Programs_id`)
    REFERENCES `khomiakov_ip22_db`.`programs` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `is_registered` INT NOT NULL DEFAULT '0',
  `Coach_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Coach1_idx` (`Coach_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Coach1`
    FOREIGN KEY (`Coach_id`)
    REFERENCES `khomiakov_ip22_db`.`coach` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `khomiakov_ip22_db`.`user_has_programs_has_coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `khomiakov_ip22_db`.`user_has_programs_has_coach` (
  `User_id` INT UNSIGNED NOT NULL,
  `Programs_has_Coach_Programs_id` INT UNSIGNED NOT NULL,
  `Programs_has_Coach_Coach_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`User_id`, `Programs_has_Coach_Programs_id`, `Programs_has_Coach_Coach_id`),
  INDEX `fk_User_has_Programs_has_Coach_Programs_has_Coach1_idx` (`Programs_has_Coach_Programs_id` ASC, `Programs_has_Coach_Coach_id` ASC) VISIBLE,
  INDEX `fk_User_has_Programs_has_Coach_User1_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Programs_has_Coach_Programs_has_Coach1`
    FOREIGN KEY (`Programs_has_Coach_Programs_id` , `Programs_has_Coach_Coach_id`)
    REFERENCES `khomiakov_ip22_db`.`programs_has_coach` (`Programs_id` , `Coach_id`),
  CONSTRAINT `fk_User_has_Programs_has_Coach_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `khomiakov_ip22_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
