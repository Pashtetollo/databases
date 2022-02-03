
-- -----------------------------------------------------
-- Schema ubergym
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ubergym` DEFAULT CHARACTER SET utf8 ;
USE `ubergym` ;

-- -----------------------------------------------------
-- Table `ubergym`.`coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`coach` (
                                                 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NOT NULL,
                                                 `surname` VARCHAR(45) NOT NULL,
                                                 `phone_number` VARCHAR(45) NULL DEFAULT NULL,
                                                 `price` DOUBLE NULL DEFAULT NULL,
                                                 PRIMARY KEY (`id`),
                                                 INDEX `SURNAME_IND` (`surname` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubergym`.`muscle_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`muscle_groups` (
                                                         `id` INT NOT NULL AUTO_INCREMENT,
                                                         `name` VARCHAR(45) NULL DEFAULT NULL,
                                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubergym`.`exersices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`exersices` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(45) NULL DEFAULT NULL,
                                                     `targeted_bodypart` INT NOT NULL,
                                                     PRIMARY KEY (`id`),
                                                     UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
                                                     INDEX `targeted_bodypart_IND` (`targeted_bodypart` ASC) VISIBLE,
                                                     CONSTRAINT `fk_Exersices_Muscle_groups1`
                                                         FOREIGN KEY (`targeted_bodypart`)
                                                             REFERENCES `ubergym`.`muscle_groups` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubergym`.`programs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`programs` (
                                                    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `name` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`id`),
                                                    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubergym`.`programs_has_exercises`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`programs_has_exercises` (
                                                                  `Programs_id` INT UNSIGNED NOT NULL,
                                                                  `Exersices_id` INT NOT NULL,
                                                                  `exercise_duration` INT NULL DEFAULT NULL,
                                                                  `number_of_repetitions` INT NULL DEFAULT NULL,
                                                                  `number_of_sets` INT NULL DEFAULT NULL,
                                                                  PRIMARY KEY (`Exersices_id`, `Programs_id`),
                                                                  INDEX `fk_Programs_has_Exercises_Programs1_idx` (`Programs_id` ASC) VISIBLE,
                                                                  INDEX `fk_Programs_has_Exercises_Exersices_idx` (`Exersices_id` ASC) VISIBLE,
                                                                  CONSTRAINT `fk_Programs_has_Exercises_Exersices`
                                                                      FOREIGN KEY (`Exersices_id`)
                                                                          REFERENCES `ubergym`.`exersices` (`id`),
                                                                  CONSTRAINT `fk_Programs_has_Exercises_Programs1`
                                                                      FOREIGN KEY (`Programs_id`)
                                                                          REFERENCES `ubergym`.`programs` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubergym`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubergym`.`user` (
                                                `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                `surname` VARCHAR(45) NOT NULL,
                                                `name` VARCHAR(45) NOT NULL,
                                                `programs_id` INT UNSIGNED NULL,
                                                `coach_id` INT UNSIGNED NULL,
                                                PRIMARY KEY (`id`),
                                                INDEX `SURNAME_IND` (`surname` ASC) VISIBLE,
                                                INDEX `fk_user_programs1_idx` (`programs_id` ASC) VISIBLE,
                                                INDEX `fk_user_coach1_idx` (`coach_id` ASC) VISIBLE,
                                                CONSTRAINT `fk_user_programs1`
                                                    FOREIGN KEY (`programs_id`)
                                                        REFERENCES `ubergym`.`programs` (`id`)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION,
                                                CONSTRAINT `fk_user_coach1`
                                                    FOREIGN KEY (`coach_id`)
                                                        REFERENCES `ubergym`.`coach` (`id`)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO coach (name, surname, phone_number, price)
VALUES ('Bohdan', 'Pervka', '+380124764323', 999.99),
       ('Petro', 'Pervak', '+380124425735', 700),
       ('Shtuk', 'Toblerone', '+380124768355', 532),
       ('Oleh', 'Nevermore', '+38012470089', 1200),
       ('Danylo', 'Danyil', '+380676666638', 690),
       ('Denis', 'Corona', '+380421345754', 777),
       ('Orest', 'Pox', '+380346758534', 200),
       ('Kai', 'Pai', '+380235346574', 599),
       ('Dobrynia', 'Nikituch', '+380756535243', 2200),
       ('Serhio', 'Gonzalez', '+380656234299', 9999);

INSERT INTO programs (name)
VALUES ('UltraPower9000'),
       ('MonkeyStrong'),
       ('ForWeaklings'),
       ('Regular'),
       ('Advanced');

INSERT INTO muscle_groups(name)
VALUES ('Body core'),
       ('Rotator cuff'),
       ('Abdominals'),
       ('Legs'),
       ('Arms'),
       ('Chest'),
       ('Back'),
       ('Obliques');



INSERT INTO exersices(name, targeted_bodypart)
VALUES ('Plank', 1),
       ('Push-ups', 6),
       ('Sit-ups', 3),
       ('Pull-ups', 7),
       ('Fire hydrant', 8),
       ('Russian twists', 8),
       ('Dumbell curls', 5),
       ('Face pull-ups', 2),
       ('Squats', 4);


INSERT INTO programs_has_exercises(Programs_id, Exersices_id, exercise_duration,
                                   number_of_repetitions, number_of_sets)
VALUES (1, 4, 60, 1, 5),
       (1, 3, 45, 20, 5),
       (1, 8, 120, 80, 5),
       (2, 5, 60, 15, 2),
       (2, 3, 45, 20, 3),
       (3, 6, 30, 10, 4),
       (3, 1, 100, 20, 3),
       (4, 2, 80, 30, 2),
       (5, 5, 20, 35, 1),
       (5, 7, 40, 10, 2);


INSERT INTO user (surname, name,coach_id, programs_id)
VALUES ('Didko','Bohdan', 2,4),
       ('Dido','Oleh', 4,3),
       ('Babko','Orest', 5,3),
       ('Okliy','Serhio', 4,2),
       ('Khot','Denis', 5,1),
       ('Bezryk','Oleh', 8,4),
       ('Shevko','Dobrynia', 6,5),
       ('Minust','Serhio', 3,5),
       ('Lemint','Denis', 2,4),
       ('Joeye','Orest', 1,2),
       ('Aboba','Oleh', 1,3),
       ('Lupen','Orest', 7,4),
       ('Qwawa','Dobrynia', 7,5),
       ('Potatoman','Serhio',9,3);