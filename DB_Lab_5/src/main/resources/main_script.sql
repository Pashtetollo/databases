- programs_has_coach-- -----------------------------------------------------
-- Schema UberGym
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ubergym` DEFAULT CHARACTER SET utf8;
USE
`ubergym` ;
DROP TABLE IF EXISTS user_has_programs_has_coach;
DROP TABLE IF EXISTS programs_has_exercises;
DROP TABLE IF EXISTS programs_has_coach;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS exersices;
DROP TABLE IF EXISTS muscle_groups;
-- -----------------------------------------------------
-- Table `UberGym`.`coach`
-- -----------------------------------------------------
CREATE TABLE coach
(
    `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL,
    `surname`      VARCHAR(45) NOT NULL,
    `phone_number` VARCHAR(45) NULL,
    `price`        DECIMAL(8, 2) NULL,
    PRIMARY KEY (`id`),
    INDEX          SURNAME_IND(surname ASC)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`muscle_groups`
-- -----------------------------------------------------
CREATE TABLE muscle_groups
(
    `id`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`exersices`
-- -----------------------------------------------------
CREATE TABLE exersices
(
    `id`                INT NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(45) NULL DEFAULT NULL,
    `targeted_bodypart` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX               `targeted_bodypart_IND` (`targeted_bodypart` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    CONSTRAINT `fk_Exersices_Muscle_groups1`
        FOREIGN KEY (`targeted_bodypart`)
            REFERENCES `UberGym`.`muscle_groups` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`programs`
-- -----------------------------------------------------
CREATE TABLE programs
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`programs_has_exercises`
-- -----------------------------------------------------
CREATE TABLE programs_has_exercises
(
    `Programs_id`           INT UNSIGNED NOT NULL,
    `Exersices_id`          INT NOT NULL,
    `exercise_duration`     INT NULL DEFAULT NULL,
    `number_of_repetitions` INT NULL DEFAULT NULL,
    `number_of_sets`        INT NULL DEFAULT NULL,
    PRIMARY KEY (`Exersices_id`, `Programs_id`),
    INDEX                   `fk_Programs_has_Exercises_Programs1_idx` (`Programs_id` ASC) VISIBLE,
    INDEX                   `fk_Programs_has_Exercises_Exersices_idx` (`Exersices_id` ASC) VISIBLE,
    CONSTRAINT `fk_Programs_has_Exercises_Exersices`
        FOREIGN KEY (`Exersices_id`)
            REFERENCES `UberGym`.`exersices` (`id`),
    CONSTRAINT `fk_Programs_has_Exercises_Programs1`
        FOREIGN KEY (`Programs_id`)
            REFERENCES `UberGym`.`programs` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`programs_has_coach`
-- -----------------------------------------------------
CREATE TABLE programs_has_coach
(
    `Programs_id` INT UNSIGNED NOT NULL,
    `Coach_id`    INT UNSIGNED NOT NULL,
    PRIMARY KEY (`Programs_id`, `Coach_id`),
    INDEX         `fk_Programs_has_Coach_Coach1_idx` (`Coach_id` ASC) VISIBLE,
    INDEX         `fk_Programs_has_Coach_Programs1_idx` (`Programs_id` ASC) VISIBLE,
    CONSTRAINT `fk_Programs_has_Coach_Coach1`
        FOREIGN KEY (`Coach_id`)
            REFERENCES `UberGym`.`coach` (`id`),
    CONSTRAINT `fk_Programs_has_Coach_Programs1`
        FOREIGN KEY (`Programs_id`)
            REFERENCES `UberGym`.`programs` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`user`
-- -----------------------------------------------------
CREATE TABLE user
(
    `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `surname`  VARCHAR(45) NOT NULL,
    `Coach_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    INDEX      SURNAME_IND(surname ASC),
    INDEX      `fk_User_Coach1_idx` (`Coach_id` ASC) VISIBLE,
    CONSTRAINT `fk_User_Coach1`
        FOREIGN KEY (`Coach_id`)
            REFERENCES `UberGym`.`coach` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `UberGym`.`user_has_programs_has_coach`
-- -----------------------------------------------------
CREATE TABLE user_has_programs_has_coach
(
    `User_id`                        INT UNSIGNED NOT NULL,
    `Programs_has_Coach_Programs_id` INT UNSIGNED NOT NULL,
    `Programs_has_Coach_Coach_id`    INT UNSIGNED NOT NULL,
    PRIMARY KEY (`User_id`, `Programs_has_Coach_Programs_id`, `Programs_has_Coach_Coach_id`),
    INDEX                            `fk_User_has_Programs_has_Coach_Programs_has_Coach1_idx` (`Programs_has_Coach_Programs_id` ASC, `Programs_has_Coach_Coach_id` ASC) VISIBLE,
    INDEX                            `fk_User_has_Programs_has_Coach_User1_idx` (`User_id` ASC) VISIBLE,
    FOREIGN KEY (`Programs_has_Coach_Programs_id`, `Programs_has_Coach_Coach_id`)
        REFERENCES `UberGym`.`programs_has_coach` (`Programs_id`, `Coach_id`),
    FOREIGN KEY (`User_id`)
        REFERENCES `UberGym`.`user` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO coach (name, surname, phone_number, price)
VALUES (''Bohdan'', ''Pervka'', '' + 380124764323 '', 999.99),
       (''Petro'', ''Pervak'', '' + 380124425735 '', 700),
       (''Shtuk'', ''Toblerone'', '' + 380124768355 '', 532),
       (''Oleh'', ''Nevermore'', '' + 38012470089 '', 1200),
       (''Danylo'', ''Danyil'', '' + 380676666638 '', 690),
       (''Denis'', ''Corona'', '' + 380421345754 '', 777),
       (''Orest'', ''Pox'', '' + 380346758534 '', 200),
       (''Kai'', ''Pai'', '' + 380235346574 '', 599),
       (''Dobrynia'', ''Nikituch'', '' + 380756535243 '', 2200),
       (''Serhio'', ''Gonzalez'', '' + 380656234299 '', 9999);

INSERT INTO user (surname, Coach_id)
VALUES (''Didko'', 2),
       (''Dido'', 4),
       (''Babko'', 5),
       (''Okliy'', 4),
       (''Khot'', 5),
       (''Bezryk'', 8),
       (''Shevko'', 6),
       (''Minust'', 3),
       (''Lemint'', 2),
       (''Joeye'', 1),
       (''Aboba'', 1),
       (''Lupen'', 7),
       (''Qwawa'', 7),
       (''Potatoman'', 9);

INSERT INTO muscle_groups(name)
VALUES (''Body core''),
       (''Rotator cuff''),
       (''Abdominals''),
       (''Legs''),
       (''Arms''),
       (''Chest''),
       (''Back''),
       (''Obliques'');

INSERT INTO exersices(name, targeted_bodypart)
VALUES (''Plank'', 1),
       (''Push-ups'', 6),
       (''Sit-ups'', 3),
       (''Pull-ups'', 7),
       (''Fire hydrant'', 8),
       (''Russian twists'', 8),
       (''Dumbell curls'', 5),
       (''Face pull-ups'', 2),
       (''Squats'', 4);

INSERT INTO programs (name)
VALUES (''UltraPower9000''),
       (''MonkeyStrong''),
       (''ForWeaklings''),
       (''Regular''),
       (''Advanced'');

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

INSERT into programs_has_coach(Programs_id, Coach_id)
VALUES (''1'', ''1''),
       (''3'', ''2''),
       (''2'', ''3''),
       (''4'', ''4''),
       (''3'', ''5''),
       (''5'', ''6''),
       (''5'', ''7''),
       (''3'', ''8''),
       (''2'', ''9''),
       (''1'', ''10'');

insert into user_has_programs_has_coach(User_id, Programs_has_Coach_Coach_id, Programs_has_Coach_Programs_id)
VALUES (1, 2, 3),
       (2, 3, 2),
       (3, 4, 4),
       (4, 1, 1),
       (5, 6, 5),
       (6, 4, 4),
       (7, 3, 2),
       (8, 6, 5),
       (1, 3, 2);