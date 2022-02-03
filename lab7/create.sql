SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Khomiakov_7_44
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Khomiakov_7_44
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Khomiakov_7_44` DEFAULT CHARACTER SET utf8 ;
USE `Khomiakov_7_44` ;

-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`district`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`district` (
  `district_id` INT AUTO_INCREMENT NOT NULL,
  `district_name` VARCHAR(45) NULL,
  PRIMARY KEY (`district_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`town`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`town` (
  `town_id` INT AUTO_INCREMENT NOT NULL,
  `town_name` VARCHAR(45) NOT NULL,
  `district_id` INT NOT NULL,
  PRIMARY KEY (`town_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`student_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`student_group` (
  `group_number` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `join_year` DATE NOT NULL,
  PRIMARY KEY (`group_number`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`student` (
  `student_id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NOT NULL,
  `student_ranking` INT NULL DEFAULT NULL,
  `birthday` DATE NOT NULL,
  `join_date` DATE NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `town_id` INT NOT NULL,
  `group_number` INT NOT NULL,
  `school_id` INT NOT NULL,
  PRIMARY KEY (`student_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`debts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`debts` (
  `subject_id` INT AUTO_INCREMENT NOT NULL ,
  `subject` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subject_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`middle_school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`middle_school` (
  `school_id` int AUTO_INCREMENT NOT NULL,
  `school_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `school_head_credentials` VARCHAR(45) NULL DEFAULT NULL,
  `town_id` INT NULL,
  PRIMARY KEY (`school_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Khomiakov_7_44.TIMETABLE (
  `insert_time` DATETIME NOT NULL,
  PRIMARY KEY (`insert_time`));

-- -----------------------------------------------------
-- Table `Khomiakov_7_44`.`student_has_debts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`student_has_debts` (
  `debt_id` INT AUTO_INCREMENT NOT NULL,
  `student_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  PRIMARY KEY (debt_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Khomiakov_7_44`.`timetable` (
  `insert_id` INT AUTO_INCREMENT NOT NULL,
  `insert_time` DATETIME NULL,
  PRIMARY KEY (`insert_time`))
ENGINE = InnoDB;