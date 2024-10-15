-- Aufgabe 1
--c
create table barcodes (
  id int generated always as identity primary key,
  taxon varchar(50) not null, -- 20 -> 50
  type varchar(1),
  voucherID varchar(16), -- 12 -> 16
  gardenID varchar(24), -- 12 -> 24
  sampleID varchar(16),-- 12 -> 16
  origin varchar(70), -- 50 -> 70
  rpoB varchar(12) unique,
  rpoC varchar(12) unique,
  matK varchar(12) unique,
  trnHpsbA varchar(12) unique,
  rpl32 varchar(12) unique,
  unique (rpoB, rpoC, matK)
);

-- Aufgabe 2
--a
insert into barcodes (taxon, type, voucherID, gardenID, sampleID, origin, rpoB, rpoC, matK, trnHpsbA, rpl32) 
select * from csvread('D:\Ausbildung\Uni\Semester 2a\Datenbanken\Aufgaben\Tabellen\barcodes.csv');

--b
select * from barcodes; -- 255 Datensätze

--c
select distinct * from barcodes; -- 255 Datensätze, keine dupletten

-- Aufgabe 3
--a
select distinct taxon from barcodes; -- 75 Werte

--b
create table taxon (
  id int generated always as identity primary key,
  name varchar(50) not null unique
);

--c
insert into taxon(name) select distinct taxon from barcodes;

--d
select * from taxon; -- 75 Werte

--e
alter table barcodes add column taxonid int; -- Wert: null

--f Die Spalte c11 in Tabelle t1 wird basierend auf der Bedingung c12=c22 aus der Tabelle t2 aktualisiert
 
--g
update barcodes set taxonid=(select id from taxon where taxon.name=barcodes.taxon);
select * from barcodes;

--h
alter table barcodes add constraint key_to_taxon foreign key(taxonid) references taxon;

--i
alter table barcodes drop column taxon;
