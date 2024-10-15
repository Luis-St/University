--Aufgabe 1

Drop table ATTRAKTIONEN;
Drop table STAEDTE;

create table Staedte (
  id int generated always as identity primary key,
  name varchar(64),
  einwohner bigint,
  flache bigint,
  Land varchar(50)
);

create table Attraktionen (
  id int generated always as identity primary key,
  name varchar(64),
  beschreibung varchar(50),
  stadtId int REFERENCES Staedte
);

insert into STAEDTE(Name, Einwohner, Flache, Land) values('Berlin', 2, 5, 'Deutschland');
insert into STAEDTE(Name, Einwohner, Flache, Land) values('Köln', 3, 1, 'Deutschland');
insert into STAEDTE(Name, Einwohner, Flache, Land) values('München', 1, 100, 'Deutschland');

insert into ATTRAKTIONEN(Name, Beschreibung, StadtId) values('Berliner Fernsehturm', 'Gute aussicht', 1);
insert into ATTRAKTIONEN(Name, Beschreibung, StadtId) values('Brandenburgertor', 'Must seen', 1);

--Aufgabe 2
alter table STAEDTE alter column laengengrad decimal(4, 2);
alter table STAEDTE alter column breitengrad decimal(4, 2);

update STAEDTE set laengengrad = 10.24, breitengrad = 20.45 where id = 1;
update STAEDTE set laengengrad = 15.36, breitengrad = 2.74 where id = 2;
update STAEDTE set laengengrad = 1.98, breitengrad = 22.34 where id = 3;


alter table ATTRAKTIONEN drop column BESCHREIBUNG;

--Aufgabe 3
create table reise(
  id int generated always as identity primary key,
  ziel varchar(20),
  preis int,
  dauer int,
  verkehrsmittel varchar(20),
  anfangsdatum date,
  constraint PruefePreis check (preis >= 0),
  constraint Duration check (dauer >= 0),
  constraint Teuer check (preis >= 50 * dauer)
);
drop table reise;

create table ziel (
  id int generated always as identity primary key,  
  name varchar(50),
  land varchar(50)
);
create table vehrkehr (
  id int generated always as identity primary key,
  type varchar(50),
  sitzplätze int
);
create table reise (
  id int generated always as identity primary key,
  idZiel int REFERENCES Ziel,
  idVerkehr int REFERENCES VEHRKEHR,
  preis int,
  dauer int,
  start date,
  constraint PruefePreis check (preis >= 0),
  constraint Duration check (dauer >= 0),
  constraint Teuer check (preis >= 50 * dauer)
);

INSERT INTO ZIEL (Land, Name) VALUES('Spanien', 'Madrid');
INSERT INTO ZIEL (Land, Name) VALUES('Deutschland', 'Berlin');
INSERT INTO ZIEL (Land, Name) VALUES('Italien', 'Rom');

INSERT INTO VEHRKEHR(TYPE , SITZPLÄTZE ) VALUES('Auto', 4);
INSERT INTO VEHRKEHR(TYPE , SITZPLÄTZE ) VALUES('Zug', 100);
INSERT INTO VEHRKEHR(TYPE , SITZPLÄTZE ) VALUES('Flugzeug', 360);

INSERT INTO REISE (IDZIEL , IDVERKEHR , PREIS , DAUER , START ) VALUES(1, 1, 250, 4, '2023-10-23');
INSERT INTO REISE (IDZIEL , IDVERKEHR , PREIS , DAUER , START ) VALUES(2, 2, 400, 7, '2023-10-24');
INSERT INTO REISE (IDZIEL , IDVERKEHR , PREIS , DAUER , START ) VALUES(3, 3, 100, 2, '2023-10-25');


-- Aufgabe 4
select distinct SIBSP from TITANIC;
select distinct EMBARKED from TITANIC;
select distinct PARCH from TITANIC;

alter table TITANIC add constraint PruefeUeberleben check (SURVIVED = 1 OR SURVIVED = 0);
alter table TITANIC alter column FARE set not null;
alter table TITANIC alter column TICKET set not null;
alter table TITANIC alter column PARCH set not null;
alter table TITANIC alter column SIBSP set not null;
alter table TITANIC alter column SEX set not null;
alter table TITANIC alter column NAME set not null;
alter table TITANIC alter column PASSENGERCLASS set not null;
alter table TITANIC alter column PASSENGERID set not null;

create table Kuerzel (
  id int generated always as identity primary key,
  ort varchar(50)
);

insert into KUERZEL(Ort) select distinct EMBARKED from TITANIC where EMBARKED is not null;

alter table KUERZEL add column Name varchar(50);
update KUERZEL set NAME = 'Southampton' where id = ;
update KUERZEL set NAME = 'Queenstown' where id = 2;
update KUERZEL set NAME = 'Cherbourg' where id = 1;

SELECT * FROM KUERZEL;
