--Aufgabe 1
--a Die Spalte c darf nicht nullable sein

--b
create table t(
  c int primary key
);

--Aufgabe 2
--a Aus Tabelle t1 kann nichts gelöscht werden da Tabelle t2 von ihr abhängt

--b 
create table t1(
  id int primary key
);
insert into t1 values(4711);
create table t2(
  id int primary key,
  c int references t1 on delete cascade
);
insert into t2 values(0, 4711);
drop table t2; -- Hinzugefügte Anweisung
delete from t1;

--c Im Falle der Löschung eines Eintrags oder der gesamten Tabelle wird die Spalte c auf null gesetzt

--d Im Falle der Löschung eines Entrags, löscht das DBMS automatisch referenzierungen in anderen Tabellen

--e 
delete from t1;
delete from t2;
create table t3(
  id int primary key,
  c int references t2 on delete cascade
);

--f 
alter table t1 add c int;

--g 
alter table t1 add constraint key_to_t3 foreign key(c) references t3;

--h Siehe OneNote

--i Es ist nicht möglich einen Datensatz in Tabelle t1, t2, t3 einzufügen da die Referenzierungen dies blockieren

--j
create table t1(
  id int primary key
);
create table t2(
  id int primary key,
  c int references t1 on delete cascade
);

create table t3(
  id int primary key,
  c int references t2 on delete cascade
);

alter table t1 add c int;
alter table t1 add constraint unique_c unique (c);
insert into t1 values(0, 1);
insert into t2 values(1, 0);
insert into t3 values (2, 1);
alter table t1 add constraint key_to_t3 foreign key(c) references t3;

--k Es werden die Datensätzen die auf den gelöschten Datensatz referenzieren automatisch mit gelöscht

--Aufgabe 3 (Tabelle embarked heißt Kuerzel bei mir) 
--a
alter table titanic add column embarked_id int;

--b
alter table titanic add constraint key_to_embarked_id foreign key (embarked_id) references kuerzel;

--c 
update titanic set embarked_id=1 where embarked='C';
update titanic set embarked_id=2 where embarked='Q';
update titanic set embarked_id=3 where embarked='S';

--d
alter table titanic drop column embarked;

--e
alter table kuerzel drop column ort;

--f
alter table kuerzel add constraint unique_embarked_name unique (name);

--g Nicht der Fall
