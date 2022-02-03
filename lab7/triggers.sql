DELIMITER $$
USE `Khomiakov_7_44`$$

-- TABLE STUDENT
drop TRIGGER if exists `Khomiakov_7_44`.`student_BEFORE_INSERT`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`student_BEFORE_INSERT` BEFORE INSERT ON `student` FOR EACH ROW
BEGIN
DECLARE is_town_exists bool;
DECLARE is_group_exists bool;
DECLARE is_school_exists bool;
 SELECT exists (select * from town where town.town_id = new.town_id) into is_town_exists; 
 SELECT exists (select * from student_group where student_group.group_number = new.group_number) into is_group_exists;
 SELECT exists (select * from middle_school where middle_school.school_id = new.school_id) into is_school_exists;
	if not (is_town_exists and is_group_exists and is_school_exists)
	then signal sqlstate "45000" set message_text = 'no such town or group or school exists';
	end if;
END$$


drop TRIGGER if exists student_BEFORE_UPDATE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`student_BEFORE_UPDATE` BEFORE UPDATE ON `student` FOR EACH ROW
BEGIN
DECLARE is_town_exists bool;
DECLARE is_group_exists bool;
DECLARE is_school_exists bool;
 SELECT exists (select * from town where town.town_id = new.town_id) into is_town_exists; 
 SELECT exists (select * from student_group where student_group.group_number = new.group_number) into is_group_exists;
 SELECT exists (select * from middle_school where middle_school.school_id = new.school_id) into is_school_exists;
	if not (is_town_exists and is_group_exists and is_school_exists)
	then signal sqlstate "45000" set message_text = 'no such town or group or school exists';
	end if;
END$$

drop TRIGGER if exists student_BEFORE_DELETE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`student_BEFORE_DELETE` BEFORE DELETE ON `student` FOR EACH ROW
BEGIN
DECLARE is_student_used bool;
 SELECT exists (select * from student_has_debts where student_has_debts.student_id = old.student_id) into is_student_used; 
	if is_student_used
	then signal sqlstate "45000" set message_text = 'student still has some debts';
	end if;
END$$

-- TABLE student_group

drop TRIGGER if exists group_BEFORE_DELETE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`group_BEFORE_DELETE` BEFORE DELETE ON `student_group` FOR EACH ROW
BEGIN
DECLARE is_group_used bool;
SELECT exists (select * from student where student.group_number = old.group_number) into is_group_used;
    if is_group_used
    then signal sqlstate "45000" set message_text = 'some students still are part of this group';
    end if;
END$$

-- TABLE middle_school

drop TRIGGER if exists middle_school_BEFORE_INSERT $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`middle_school_BEFORE_INSERT` BEFORE INSERT ON `middle_school` FOR EACH ROW
BEGIN
DECLARE is_town_present bool;
    SELECT exists (select * from town where town.town_id = new.town_id) into is_town_present;
    if not is_town_present
    then signal sqlstate "45000" set message_text = 'no such town exists';
	end if;
END$$


drop TRIGGER if exists middle_school_BEFORE_UPDATE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`middle_school_BEFORE_UPDATE` BEFORE UPDATE ON `middle_school` FOR EACH ROW
BEGIN
DECLARE is_town_present bool;
    SELECT exists (select * from town where town.town_id = new.town_id) into is_town_present;
    if not is_town_present
    then signal sqlstate "45000" set message_text = 'no such town exists';
	end if;
END$$

-- TABLE student_has_debts

drop TRIGGER if exists student_has_debts_BEFORE_INSERT $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`student_has_debts_BEFORE_INSERT` BEFORE INSERT ON `student_has_debts` FOR EACH ROW
BEGIN
DECLARE is_student_exists bool;
DECLARE is_debt_exists bool;
SELECT exists (select * from student where student.student_id = new.student_id) into is_student_exists;
SELECT exists (select * from debts where debts.subject_id = new.subject_id) into is_debt_exists;
    if not (is_student_exists and is_debt_exists)
    then signal sqlstate "45000" set message_text = 'no such student or debt exists';
    end if;
END$$


drop TRIGGER if exists student_has_debts_BEFORE_UPDATE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`student_has_debts_BEFORE_UPDATE` BEFORE UPDATE ON `student_has_debts` FOR EACH ROW
BEGIN
DECLARE is_student_exists bool;
DECLARE is_debt_exists bool;
SELECT exists (select * from student where student.student_id = new.student_id) into is_student_exists;
SELECT exists (select * from debts where debts.subject_id = new.subject_id) into is_debt_exists;
    if not (is_student_exists and is_debt_exists)
    then signal sqlstate "45000" set message_text = 'no such student or debt exists';
    end if;
END$$

-- TABLE debts

drop TRIGGER if exists debts_BEFORE_DELETE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`debts_BEFORE_DELETE` BEFORE DELETE ON `debts` FOR EACH ROW
BEGIN
DECLARE is_debt_used bool;
SELECT exists (select * from student_has_debts where student_has_debts.subject_id = old.subject_id) into is_debt_used;
    if is_debt_used
    then signal sqlstate "45000" set message_text = 'some students still have debt for this subject';
    end if;
END$$

-- TABLE district

drop TRIGGER if exists district_BEFORE_DELETE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`district_BEFORE_DELETE` BEFORE DELETE ON `district` FOR EACH ROW
BEGIN
DECLARE is_district_used bool;
SELECT exists (select * from town where town.district_id = old.district_id) into is_district_used;
    if is_district_used
    then signal sqlstate "45000" set message_text = 'some towns are still a part of district';
    end if;
END$$

-- TABLE town

drop TRIGGER if exists town_BEFORE_INSERT $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`town_BEFORE_INSERT` BEFORE INSERT ON `town` FOR EACH ROW
BEGIN
	DECLARE is_district_present bool;
    SELECT exists (select * from district where district.district_id = new.district_id) into is_district_present;
    if not is_district_present
    then signal sqlstate "45000" set message_text = 'no such district exists';
	end if;
END$$


drop TRIGGER if exists town_BEFORE_UPDATE $$
CREATE DEFINER = CURRENT_USER TRIGGER `Khomiakov_7_44`.`town_BEFORE_UPDATE` BEFORE UPDATE ON `town` FOR EACH ROW
BEGIN
	DECLARE is_district_present bool;
    SELECT exists (select * from district where district.district_id = new.district_id) into is_district_present;
    if not is_district_present
    then signal sqlstate "45000" set message_text = 'no such district exists';
	end if;
END$$

-- -----------------------------
-- student
-- ------------------------------

drop TRIGGER if exists student_name_BEFORE_INSERT $$
CREATE TRIGGER student_name_BEFORE_INSERT
 BEFORE INSERT ON student FOR EACH ROW
BEGIN
    if (new.name not in ('Світлана', 'Петро', 'Оля', 'Тарас', 'Василь', 'Антон'))
        then signal sqlstate "45000" set message_text = 'Not allowed name provided';
    end if;
END$$


-- ------------------------------
-- middle_school
-- ------------------------------
drop TRIGGER if exists middle_school_number_BEFORE_INSERT $$
CREATE TRIGGER middle_school_number_BEFORE_INSERT BEFORE INSERT ON middle_school FOR EACH ROW 
BEGIN
    if( new.phone NOT REGEXP'^[0-9][0-9][0-9]\-[0-9][0-9][0-9]\-[0-9][0-9]\-[0-9][0-9]')
    then signal sqlstate "45000" set message_text = 'wrong phone format, required is XXX-XXX-XX-XX';
    end if;
END$$


-- ------------------------------
-- Town
-- ------------------------------
drop TRIGGER if exists Town_timestamp_AFTER_INSERT $$
CREATE TRIGGER Town_timestamp_AFTER_INSERT AFTER INSERT ON town FOR EACH ROW
BEGIN
  INSERT INTO Khomiakov_7_44.timetable (insert_time) VALUES (CURRENT_TIMESTAMP);
END$$

DELIMITER ;