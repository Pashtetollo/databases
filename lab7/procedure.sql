USE Khomiakov_7_44;
DELIMITER $$


-- DROP procedure IF EXISTS `insert_student`;

CREATE PROCEDURE insert_student(
 in  student_name VARCHAR(45),
 in  surname VARCHAR(45),
 in  patronymic VARCHAR(45),
 in  student_ranking INT,
 in  birthday DATE,
 in  join_date DATE,
 in  email VARCHAR(30),
 in  town_id INT,
 in  group_number INT,
 in school_id INT
)
BEGIN
    INSERT INTO Khomiakov_7_44.student 
        (name, surname,patronymic, student_ranking,birthday,join_date,
         email, town_id, group_number,school_id) 
    VALUES 
        (student_name, surname, patronymic, student_ranking, birthday, join_date, email,
         town_id, group_number,school_id);
END$$

-- DROP procedure IF EXISTS `fill_town`;
CREATE PROCEDURE fill_town()
BEGIN
    declare counter int default 0;
    declare town_num int default 0;
    declare expected_new_town varchar(9);
    declare any_district_id INT;
    (select district_id from district limit 1) into any_district_id;
    while counter < 10 do
        set expected_new_town = concat("Noname", town_num);
        if not exists(select * from Town where town.town_name = expected_new_town)
            then 
            insert into town(town_name,district_id) values (expected_new_town, any_district_id);
        set counter = counter +1;
        end if;
        set town_num = town_num + 1;
    end while;
END$$


CREATE PROCEDURE `create_db_oblast`()
BEGIN
DECLARE done int DEFAULT false;
declare counter int default 0;
declare tablenumber int;
declare db_district_name varchar(45);
DECLARE generate_db CURSOR
FOR SELECT distinct district_name from district;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
OPEN generate_db;
myLoop: LOOP
    FETCH generate_db INTO db_district_name;
    IF done=true THEN LEAVE myLoop;
    END IF;
    SET @temp_query=CONCAT('CREATE DATABASE if not exists ',
    db_district_name);
    PREPARE myquery FROM @temp_query;
    EXECUTE myquery;
    set counter = 1;
    set tablenumber = (SELECT ROUND(RAND()*(11-1)+1));
    while (counter <= tablenumber) DO
        SET @temp_query=CONCAT('CREATE TABLE if not exists ', db_district_name,'.',db_district_name,counter,'(number int)');
        PREPARE myquery FROM @temp_query;
        EXECUTE myquery;
        DEALLOCATE PREPARE myquery;
        set counter = counter +1;
    end while;
END LOOP;
CLOSE generate_db;
END$$