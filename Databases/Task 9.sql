--Aufgabe 1

--Fakultät
create table faculty (
    id int generated always as identity primary key,
    name varchar(64) not null unique
);

--Dozent
create table lecturer (
    id int generated always as identity primary key,
    name varchar(64) not null, -- Es kann mehrere Dozenten mit dem gleichen Namen geben
    faculty_id int not null references faculty
);

--Vorlesung
create table lecture (
    id int generated always as identity primary key,
    name varchar(64) not null unique, -- Es wird angenommen, dass es keine zwei Vorlesungen mit dem gleichen Namen gibt
    lecturer_id int not null references lecturer,
    participant_id int
);

--Student
create table student (
    matriculation_number int generated always as identity primary key,
    name varchar(64) not null unique,
    faculty_id int not null references faculty,
    participant_id int
);

--Teilnehmer
create table participant (
    id int generated always as identity primary key,
    lecture_id int not null references lecture,
    student_id int not null references student,
    unique (lecture_id, student_id)
);

insert into faculty (name) values
    ('Informatik');
insert into lecturer (name, faculty_id) values
    ('Prof. Dr. Lothar Piepmeyer', 1),
    ('Prof. Dr. habil. Olaf Neiße', 1),
    ('Prof. Dr. Richard Zahoransky', 1);

insert into lecture (name, lecturer_id) values
    ('Datenbanken', 1),
    ('Mathematik für Informatiker 1', 2),
    ('Mathematik für Informatiker 2', 3),
    ('Algorithmen und Datenstrukturen', 4);

insert into student (name, faculty_id) values
    ('Luis Staudt', 1),
    ('Dominik Meuer', 1),
    ('Marvin Kern', 1);

insert into participant (student_id, lecture_id) values
    (1, 1), (1, 2), (1, 4),
    (2, 2), (2, 3),
    (3, 1), (3, 3);
