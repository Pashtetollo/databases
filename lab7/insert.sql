INSERT INTO student_group(name, join_year) values ('IP1','2011-09-12');
INSERT INTO student_group(name, join_year) values ('IP2','2020-11-11');
INSERT INTO student_group(name, join_year) values ('IP3','2025-07-15');
INSERT INTO student_group(name, join_year) values ('KM1','2012-04-01');
INSERT INTO student_group(name, join_year) values ('IP2','2017-09-23');
INSERT INTO student_group(name, join_year) values ('IP3','2015-03-08');
INSERT INTO student_group(name, join_year) values ('PM1','2026-12-01');
INSERT INTO student_group(name, join_year) values ('PM2','2011-09-09');
INSERT INTO student_group(name, join_year) values ('PM3','2008-06-01');
INSERT INTO student_group(name, join_year) values ('DK4','2009-01-07');

insert into district(district_name) values('Poltava');
insert into district(district_name) values('Kyiv');
insert into district(district_name) values('Lviv');
insert into district(district_name) values('Ternopil');
insert into district(district_name) values('Karpaty');
insert into district(district_name) values('Donetsk');
insert into district(district_name) values('Rivne');
insert into district(district_name) values('Kharkiv');
insert into district(district_name) values('Ivanofrankivsk');
insert into district(district_name) values('Zhytomyr');

insert into town(town_name,district_id) values('Kolomiya',1);
insert into town(town_name,district_id) values('California',2);
insert into town(town_name,district_id) values('Minsk',3);
insert into town(town_name,district_id) values('Tallin',3);
insert into town(town_name,district_id) values('Chortkiv',4);
insert into town(town_name,district_id) values('Nevoronizh',7);
insert into town(town_name,district_id) values('Voronizh',5);
insert into town(town_name,district_id) values('Rostov',9);
insert into town(town_name,district_id) values('Krivii Rig',8);
insert into town(town_name,district_id) values('HappyLand',10);

insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola1', '099-182-72-89','Zubenko Myhailo', '3');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola2', '068-533-44-83','Orlenko Myhailo', '3');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola3', '099-213-31-32','Zubenko DMytrii', '7');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola4', '099-143-76-12','Pan Petro', '4');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola5', '067-245-54-34','Oleksandr Rybak', '5');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola6', '099-543-23-76','Slim Shady', '5');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola7', '099-754-43-45','Oksana Horshchyk', '8');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola8', '099-321-22-21','Vasiliy Kinder', '7');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola9', '099-453-73-11','Oleksiy Gogol', '10');
insert into middle_school(school_name, phone, school_head_credentials, town_id) values(
'Shkola10', '039-412-22-21','Oleksiy Petrysh', '10');

insert into debts(subject) values('Databases');
insert into debts(subject) values('Programming');
insert into debts(subject) values('Math');
insert into debts(subject) values('Music');
insert into debts(subject) values('Literature');
insert into debts(subject) values('Sport');
insert into debts(subject) values('Project');
insert into debts(subject) values('Art');
insert into debts(subject) values('English');
insert into debts(subject) values('Other');

insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Петро','Korzh','Serhiyovich',87,'2003-09-25','2020-09-25','Oleksiii@gmail.com', 3,2,7);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Тарас','Plyzh','Maksumovich',70,'2003-11-21','2020-09-25','Oleksiii@gmail.com', 1,1,2);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Антон','Porsh','Serhiyovich',90,'2003-12-12','2020-09-25','Oleksiii@gmail.com', 3,2,4);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Петро','Verk','Vasulocich',62,'2003-06-09','2019-09-25','Oleksiii@gmail.com', 3,5,3);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Василь','Lut','Petrovych',80,'2003-04-12','2019-09-25','Oleksiii@gmail.com', 4,4,6);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Світлана','Juri','Yurivna',67,'2003-09-16','2019-09-25','Oleksiii@gmail.com', 5,5,7);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Оля','Ludko','Petrivna',40,'2003-02-12','2021-09-25','Oleksiii@gmail.com', 5,7,1);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Тарас','Lupan','Maksumovich',60,'2003-09-26','2021-09-25','Oleksiii@gmail.com', 6,8,8);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Оля','Buk','Tarasivna',95,'2003-05-07','2021-09-25','Oleksiii@gmail.com', 7,7,3);
insert into student(name,surname,patronymic,student_ranking,birthday,join_date,email,town_id,group_number,school_id) 
values('Світлана','Bek','Fedorivna',63,'2003-07-09','2021-09-25','Oleksiii@gmail.com', 9,9,2);

insert into student_has_debts(student_id,subject_id) values(2,2);
insert into student_has_debts(student_id,subject_id) values(2,3);
insert into student_has_debts(student_id,subject_id) values(1,4);
insert into student_has_debts(student_id,subject_id) values(5,5);
insert into student_has_debts(student_id,subject_id) values(4,6);
insert into student_has_debts(student_id,subject_id) values(6,7);
insert into student_has_debts(student_id,subject_id) values(7,7);
insert into student_has_debts(student_id,subject_id) values(8,4);
insert into student_has_debts(student_id,subject_id) values(5,3);
insert into student_has_debts(student_id,subject_id) values(6,8);
