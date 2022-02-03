USE `Khomiakov_7_44`;
set global log_bin_trust_function_creators = 1;
DELIMITER $$

-- DROP function IF EXISTS `count_avg_student_rank`;

CREATE FUNCTION `count_avg_student_rank` ()
RETURNS INTEGER
BEGIN
	RETURN (select avg(student_ranking) from student);
END$$


-- DROP function IF EXISTS `select_town_name`;

CREATE FUNCTION `select_town_name`(midschool_id INT)
RETURNS VARCHAR(45)
BEGIN
	RETURN (select town.town_name from town
    where town.town_id = (select town_id from middle_school where middle_school.school_id = midschool_id));
END$$

DELIMITER ;

select * from student where student.student_ranking < (select count_avg_student_rank());