--Aufgabe 1
create table partei (
    id int not null primary key,
    name varchar(20),
    vorsitzender varchar(20)
);
alter table partei alter column name set not null;
create table abgeordneter (
    name varchar(20),
    partei int references partei,
    wahlkreis varchar(20)
);

insert into partei (id, name, vorsitzender)
values (1, 'Party A', 'Leader A'),
       (2, 'Party B', 'Leader B'),
       (3, 'Party C', 'Leader C'),
       (4, 'Party D', 'Leader D'), --Not used
       (5, 'Party E', null); --No leader

insert into abgeordneter (name, partei, wahlkreis)
values ('Member 1', 1, 'District 1'),
       ('Member 2', 1, 'District 2'),
       ('Member 3', 2, 'District 3'),
       ('Member 4', 2, 'District 4'),
       ('Member 5', 3, 'District 5'),
       ('Member 6', 1, 'District 1'),
       ('Member 7', 1, 'District 2'),
       ('Member 8', 2, 'District 3'),
       ('Member 9', 2, 'District 1'),
       ('Member 10', 3, 'District 2'),
       ('Member 11', 1, 'District 3'),
       ('Member 12', null, 'District 1'),
       ('Member 13', 3, 'District 2'),
       ('Member 14', 1, 'District 3'),
       ('Member 15', null, 'District 1'),
       ('Member 16', null, 'District 2'),
       ('Member 17', 1, 'District 3'),
       ('Member 18', 2, 'District 1'),
       ('Member 19', 3, 'District 2'),
       ('Member 20', 1, 'District 3'),
       ('Member 21', 2, 'District 1'),
       ('Member 22', null, 'District 2'),
       ('Member 23', null, 'District 3'),
       ('Member 24', null, 'District 1'),
       ('Member 25', null, 'District 2');

update abgeordneter set wahlkreis = 'District 4' where wahlkreis = 'District 3';

create table wahlkreis (
    id int generated always as identity primary key,
    name varchar(16),
    popularity int default 1 not null,
    unique(name),
    check(popularity > 0)
);

insert into wahlkreis (name)
values ('District 1'),
       ('District 2'),
       ('District 3'),
       ('District 4'),
       ('District 5');

alter table abgeordneter add column wahlkreis_id int;

update abgeordneter set wahlkreis_id = 1 where wahlkreis = 'District 1';
update abgeordneter set wahlkreis_id = 2 where wahlkreis = 'District 2';
update abgeordneter set wahlkreis_id = 3 where wahlkreis = 'District 3';
update abgeordneter set wahlkreis_id = 4 where wahlkreis = 'District 4';
update abgeordneter set wahlkreis_id = 5 where wahlkreis = 'District 5';

alter table abgeordneter alter column wahlkreis_id set not null;
alter table abgeordneter add constraint key_to_wahlkreis foreign key (wahlkreis_id) references wahlkreis;

insert into abgeordneter (name, partei, wahlkreis_id)
values ('Member 1', 1, 6);

alter table abgeordneter drop column wahlkreis;



select * from partei;
select * from abgeordneter;
select * from wahlkreis;

--c
select name, vorsitzender from partei;

--d
select name, vorsitzender from partei where vorsitzender is not null;

--e
select name from abgeordneter where partei is null;

--f - i
select p.name from partei p
inner join abgeordneter a on p.id = a.partei
group by p.name; -- Alternativ wäre auch distinct möglich

--f - ii
select p.name from partei p
where p.id in (select a.partei from abgeordneter a where a.partei is not null);

--g
select a.name, p.name from abgeordneter a
left outer join partei p on a.partei = p.id;

--h
select p.name, count(a.partei) from partei p
left outer join abgeordneter a on p.id = a.partei
group by p.name;

--i
select p.name, count(a.partei) as count from partei p
left outer join abgeordneter a on p.id = a.partei
group by p.name
having count(a.partei) > 0;

--j
select p.name, count(a.partei) from partei p
left outer join abgeordneter a on p.id = a.partei
group by p.name
having count(a.partei) = (
    select max(count) from (
        select count(a.partei) as count from partei p
        left outer join abgeordneter a on p.id = a.partei
        group by p.name
    )
);

--l

--Aufgabe 2
create table author (
    aid int primary key,
    bid int not null unique,
    name varchar(20)
);
create table book (
    bid int primary key,
    aid int not null unique references author,
    title varchar(20)
);
alter table author add foreign key(bid) references book;

select * from author;
select * from book;

insert into author (aid, bid, name) values (0, 0, 'Luis');
insert into book (bid, aid, title) values (0, 0, 'Programmieren für Dumme');
