--Oleksandr Khomiakov IP22 N44

--1)
SELECT model, ram, price FROM laptop
WHERE ram = 64 ORDER BY screen ASC;

--2)
SELECT name FROM ships
WHERE name LIKE 'W%n';

--3)
SELECT model AS model_1,
(SELECT model FROM PC p WHERE p.speed = pk.speed 
AND p.model<pk.model 
AND p.ram = pk.ram 
AND p.code != pk.code) AS model_2, speed, ram FROM PC pk 
where (SELECT model FROM PC p WHERE p.speed = pk.speed 
AND p.model<pk.model 
AND p.ram = pk.ram 
AND p.code != pk.code) !='';

--4)
SELECT o.ship, o.battle, o.result, b.date FROM outcomes o
JOIN battles b ON o.battle = b.name
WHERE o.ship = ANY(
SELECT o.ship FROM outcomes o
JOIN battles b ON o.battle = b.name WHERE o.result = 'damaged' AND'' != ANY(SELECT ot.ship FROM outcomes ot
JOIN battles bt ON bt.name = ot.battle WHERE bt.date > b.date AND ot.ship = o.ship))

--5)
SELECT distinct prod.maker FROM product prod
JOIN pc p ON prod.model = p.model
WHERE prod.type = 'PC'
AND p.speed >=750;

--6)
SELECT (SELECT CONCAT(SUBSTRING(i.date,1,4),
'.',
SUBSTRING(i.date, 6,2),
'.',
SUBSTRING(i.date, 9,2))) AS date FROM Income i;

--7)
select distinct p.maker from product p 
where (select COUNT(pr.model) from product pr where
pr.model!= p.model and pr.type = 'PC' and p.maker = pr.maker)>=1 
and p.type = 'PC';

--8)
SELECT DISTINCT p.maker, MIN(l.speed) AS MIN_SPEED FROM product p 
JOIN laptop l ON l.model = p.model
WHERE (SELECT DISTINCT MIN(lp.speed) FROM product pr 
JOIN laptop lp ON lp.model = pr.model
WHERE pr.maker = p.maker
GROUP BY pr.maker)>=600
GROUP BY p.maker;

--9)
SELECT t.trip_no, c.name AS company_name, town_from, town_to, CASE
WHEN TIMEDIFF(t.time_in, t.time_out) >0
THEN TIMEDIFF(t.time_in, t.time_out)
ELSE TIMEDIFF(TIMEDIFF(t.time_in, t.time_out),"-24:00:00")
END  AS flight_duration FROM TRIP t JOIN Company c ON t.id_comp=c.id_comp;

--10)
(SELECT c.class as Ship_name FROM Classes c WHERE c.class LIKE '% %'
UNION
SELECT s.name FROM Ships s WHERE s.name LIKE '% %'
UNION
SELECT o.ship FROM Outcomes o WHERE o.ship LIKE '% %');