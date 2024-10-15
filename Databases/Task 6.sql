-- Aufgabe 1
--create table titanic (
--  id int generated always as identity primary key,
--  class varchar(128),
--  survived varchar(1),
--  name varchar(128),
--  gender varchar(128),
--  age int,
--  sibsp int,
--  parch int,
--  ticket varchar(128),
--  fare varchar(128),
--  cabin varchar(128),
--  embarked varchar(128),
--  boat varchar(128),
--  body varchar(128),
--  home varchar(128)
--);
--insert into titanic(class, survived, name, gender, age, sibsp, parch, ticket, fare, cabin, embarked, boat, body, home)
--select * from csvread('D:\Ausbildung\Uni\Semester 2a\Datenbanken\Aufgaben\Tabellen\titanic.csv');

--a
select name from titanic where ticket='A/5. 3336'; --2

--b
select name from titanic where sibsp = 0 and parch = 0; --790

--c
select distinct class from titanic; --3

--d
select name from titanic where fare is null; --1

--e
select name from titanic where fare = 0; --17

--f
select ticket, name from titanic where name = 'Astor, Col. John Jacob'; --PC 17757

--g
select name from titanic where ticket = 'PC 17757' and name != 'Astor, Col. John Jacob'; --4

--h
select name from titanic where boat is null and survived = 1; --23

--i
select name from titanic where boat is not null and survived = 0; --9

--j
select name from titanic where gender = 'male' and name not like '%Mr.%' and name not like '%Master%'; --25

--k
select boat from titanic where boat is not null; -- Da in einer Zeile mehrere Boote stehen, ist die Anzahl der Boote größer als die Anzahl der Personen, die ein Boot hatten.

--l
select name from titanic where home like '%Germany%';

--m
select name from titanic where gender = 'female' and (age >= 10 and 16 >= age); --25

--n
select name from titanic where 12 >= age and sibsp = 0 and parch = 0; --3