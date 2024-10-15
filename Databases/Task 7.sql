-- Aufgabe 1
--a
select name from titanic where ticket in ('113038', '1601', '21228', 'C 173668', 'A/5 21173', 'A./5. 2152', '54636'); --15

--b
select count(*) from titanic; --1309

--c
select survived, count(survived) from titanic group by survived; --500, 809
--select
--    count(case when survived = 1 then 1 end) as survived,
--    count(case when survived = 0 then 1 end) as died
--from titanic;

--d
select count(age) from titanic where 15 >= age; -- 0, 80
select count(age) from titanic where age > 15; -- 0, 80
select
    count(case when 15 >= age then 1 end) as young,
    count(case when age > 15 then 1 end) as old
from titanic; -- 115, 931

--e
select avg(age) as average, min(age) as youngest, max(age) as oldest from titanic; -- 29.88, 0, 80

--f
select survived, avg(age) from titanic group by survived;
--select
--    avg(case when survived = 1 then age end) as average_survived,
--    avg(case when survived = 0 then age end) as average_died
--from titanic; --28.92, 30.57

--g
select distinct boat from titanic where boat is not null and trim(boat) not like '% %';

--h
select distinct boat, count(*) from titanic where boat is not null and trim(boat) not like '% %' group by boat;

--i
select distinct boat from titanic where boat is not null and trim(boat) not like '% %' group by boat having count(*) > 25;

--j
select ticket, count(name) from titanic group by ticket having count(distinct fare) > 1;

--k
select class, survived, count(survived) from titanic group by class, survived;
--select
--    count(case when survived = 1 and class = 1 then 1 end) as survived_1,
--    count(case when survived = 0 and class = 1 then 1 end) as died_1,
--    count(case when survived = 1 and class = 2 then 1 end) as survived_2,
--    count(case when survived = 0 and class = 2 then 1 end) as died_2,
--    count(case when survived = 1 and class = 3 then 1 end) as survived_3,
--    count(case when survived = 0 and class = 3 then 1 end) as died_3
--from titanic;

