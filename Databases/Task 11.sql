--Aufgabe 1
select city, count(*) from titanic
inner join embarked on embarked.id = titanic.embarked_id
group by embarked_id;

--Aufgabe 2
--a
select name, age from titanic
where age >= (select max(age) from titanic);

--b
select count(*) from titanic
where embarked_id = (select id from embarked where city like '%town');

--c
select count(*) from titanic
where embarked_id in (select id from embarked where city like '%town');

--d
-- Abfragen die auch zum Ergebnis führen aber nicht den Regeln entsprechen:
-- Verwendung eines limits mit 'top' oder 'limit'
--select top(1) embarked_id, count(embarked_id) as count
--from titanic
--group by embarked_id
--having count(embarked_id) > 0
--order by count(embarked_id);
-- Verwendung einer Unterabfrage im 'from' Teil
--select id, city from embarked
--where id = (
--    select embarked_id from titanic
--    group by embarked_id
--    having count(embarked_id) = (
--        select min(embarked_count)
--        from (select count(embarked_id) as embarked_count from titanic
--              where embarked_id is not null
--              group by embarked_id) as counts
--        )
--    );

select e.id, e.city, count(*) as anzahl
from embarked e, titanic t
where e.id = t.embarked_id
group by t.embarked_id
having count(*) <= all(
    select count(*) from embarked e, titanic t
    where e.id = t.embarked_id
    group by t.embarked_id
);

--e
select name from titanic
where embarked_id in (
    select e.id from embarked e, titanic t
    where e.id = t.embarked_id
    group by t.embarked_id
    having count(*) <= all(
        select count(*) from embarked e, titanic t
        where e.id = t.embarked_id
        group by t.embarked_id
    )
);

--f
select name, age from titanic
where age >= all(select age from titanic);
-- Die Query gibt nichts zurück, da bei 'all' die Kondition für alle Elemente erfüllt sein muss.
-- Dies ist aber wenn die query null Werte enthält nicht der Fall da 'all' nicht mit null verglichen werden kann.
-- Die Query müsste wie folgt aussehen:
select name, age from titanic
where age >= all(select age from titanic where age is not null);