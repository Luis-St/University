--Aufgabe 1
drop table if exists titanic, tickets, embarked;

create table tickets(
  id int  primary key,
  name varchar(20)
);

create table embarked(
  id int  primary key,
  city varchar(20) unique
);

create  table titanic(
    class integer,
    survived integer,
    name varchar(82),
    gender varchar(6),
    age integer,
    sibsp integer,
    parch integer,
    fare integer,
    cabin varchar(15),
    boat varchar(7),
    body integer,
    home varchar(50),
    id int primary key,
    embarked_id integer references embarked,
    ticket_id integer references tickets
);

insert into embarked (select * from csvread('D:/Ausbildung/Uni/Semester 2a/Datenbanken/Aufgaben/Tabellen/embarked.csv', null, 'charset=utf-8 fieldseparator=|'));
insert into tickets (select * from csvread('D:/Ausbildung/Uni/Semester 2a/Datenbanken/Aufgaben/Tabellen/tickets.csv', null, 'charset=utf-8 fieldseparator=|'));
insert into titanic (select * from csvread('D:/Ausbildung/Uni/Semester 2a/Datenbanken/Aufgaben/Tabellen/titanic.csv', null, 'charset=utf-8 fieldseparator=|'));

select * from titanic;
select * from tickets;
select * from embarked;

--Aufgabe 2
--a
select count(*) from titanic inner join embarked e on e.id = titanic.embarked_id where e.id = 4;

--b
select count(*) from titanic where embarked_id is null;

--c
select e.city, t.name from titanic
    inner join embarked e on e.id = titanic.embarked_id
    inner join tickets t on titanic.ticket_id = t.id
where titanic.name = 'Lovell, Mr. John Hall ("Henry")';

--d
select e.city, count(*) from titanic inner join embarked e on e.id = titanic.embarked_id group by e.city;

--e
select titanic.name, t.name as ticket, t.id as ticket_Id, e.city from titanic
    inner join tickets t on t.id = titanic.ticket_id
    inner join embarked e on e.id = titanic.embarked_id
where titanic.id = 1014;

--f
select tickets.name, count(*) from titanic, tickets
where titanic.ticket_id = tickets.id
group by tickets.name
having count(*) > 1;

--g
select titanic.name, tickets.name, embarked.city
from ((titanic inner join embarked on titanic.embarked_id = embarked.id)
    inner join tickets on titanic.ticket_id = tickets.id)
where tickets.name in (
    select tickets.name from ((
        titanic inner join embarked on titanic.embarked_id = embarked.id)
        inner join tickets on titanic.ticket_id = tickets.id)
    group by tickets.name
    having count(distinct embarked.city) > 1);
